import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ClassDiagram {

    protected Map<String,String> inheritance;
    protected Map<String,Set<String>> associations;
    protected Set<String> NoRelation;

    //include classes in the JDK etc? Can produce crowded diagrams.
    protected boolean includeLibraryClasses = true;

    protected Set<String> allClassNames;

    public static List<Class<?>> processDirectory(File directory, String pkgname) {

        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        String prefix = pkgname+".";
        if(pkgname.equals(""))
            prefix = "";

        // Get the list of the files contained in the package
        String[] files = directory.list();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            String className = null;

            // we are only interested in .class files
            if (fileName.endsWith(".class")) {
                // removes the .class extension
                className = prefix+fileName.substring(0, fileName.length() - 6);
            }


            if (className != null) {
                Class loaded = loadClass(className);
                if(loaded!=null)
                    classes.add(loaded);
            }

            //If the file is a directory recursively class this method.
            File subdir = new File(directory, fileName);
            if (subdir.isDirectory()) {

                classes.addAll(processDirectory(subdir, prefix + fileName));
            }
        }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
        catch (Error e){
            return null;
        }
    }


    /**
     * Instantiating the class will populate the inheritance and association relations.
     * @param root
     * @throws IOException 
     */
    public ClassDiagram(String root, boolean includeLibs) throws IOException{
        this.includeLibraryClasses = includeLibs;
        File dir = new File(root);

        List<Class<?>> classes = processDirectory(dir,"");
        inheritance = new HashMap<String, String>();
        associations = new HashMap<String, Set<String>>();
        NoRelation = new HashSet<String>(); 

        allClassNames = new HashSet<String>();
        for(Class cl : classes){
            allClassNames.add(cl.getName());
        }
        
        // Add only trace files classes
        // Add the classes names to SET: ClassesInTraces
        Set<String> ClassesInTraces = new HashSet<String>();
        Reader classesCSV = new FileReader("../dataFiles/2D_DynamicAnalysisImportantClasses/ClassesAndMethodsCSVFiles/classes.csv");
        Iterable<CSVRecord> classesCSVIterable = CSVFormat.EXCEL.parse(classesCSV);
        int iteration = 0;
        for (CSVRecord rec : classesCSVIterable) {
	        	if(iteration == 0) {
	                iteration++;  
	                continue;
	        }
			String ClassName = rec.get(0);
			ClassName = ClassName.replaceAll("\\s","");
			ClassesInTraces.add(ClassName);	
        }
        
        
        // For each class check if it exist in the ClassesInTraces SET 
        // If exist check all the supper class and each association classes if they exist in the ClassesInTraces SET.
        // add only classes that exist in the ClassesInTraces SET 
        for(Class cl : classes){
            if(cl.isInterface())
                continue;
            if ( ClassesInTraces.contains(cl.getName()) ) {
            		if ( ClassesInTraces.contains(cl.getSuperclass().getName()) ) {
            			inheritance.put(cl.getName(),cl.getSuperclass().getName());
            		}
            		// NoRelation stores the classes which are in the ClassesInTraces SET, but have no SuperClasses.
            		else {
            			NoRelation.add(cl.getName());
            		}
            		Set<String> fields = new HashSet<String>();
                    for(Field fld : cl.getDeclaredFields()){
                        //Do not want to include associations to primitive types such as ints or doubles.
                        if(!fld.getType().isPrimitive()) {
                        		if (ClassesInTraces.contains(fld.getType().getName())) {
                        			fields.add(fld.getType().getName());
                        		}
                        }
                    }
                    if (fields != null ) {
                    	associations.put(cl.getName(),fields);
                    }
            }
            else if ( ClassesInTraces.contains(cl.getSuperclass().getName()) ) {
            		NoRelation.add(cl.getSuperclass().getName());
            }
        }
        
        for (String s : ClassesInTraces) {
        		if ( (!NoRelation.contains(s)) && (!inheritance.containsKey(s)) && (!associations.containsKey(s)) ) {
        			NoRelation.add(s);
        		}
        }
    }

    /**
     * Write out the class diagram to a specified file.
     * @param target
     */
    public void writeDot(File target) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(target));
        StringBuffer dotGraph = new StringBuffer();
        Collection<String> dotGraphClasses = new HashSet<String>(); //need this to specify that shape of each class should be a square.
        dotGraph.append("digraph classDiagram{\n" +
                "graph [splines=ortho, width=0.1, height=0.1]\n\n");

        // Add the Trace Files to HashMaps to store the class and his total occurrence and if Always used.
        HashMap<String, Integer> TotalOccur = new HashMap<String, Integer>();
        HashMap<String, Boolean> AlWaysUSed = new HashMap<String, Boolean>();
        
        Reader classesCSV = new FileReader("../dataFiles/2D_DynamicAnalysisImportantClasses/ClassesAndMethodsCSVFiles/classes.csv");
        Iterable<CSVRecord> classesCSVIterable = CSVFormat.EXCEL.parse(classesCSV);
        int iteration = 0;
        for (CSVRecord rec : classesCSVIterable) {
	        	if(iteration == 0) {
	                iteration++;  
	                continue;
	        }
			String ClassName = rec.get(0);
			ClassName = ClassName.replaceAll("\\s","");
			Integer TotalOccurrences = Integer.parseInt(rec.get(1));
			String AlwaysUsed = rec.get(2);
			TotalOccur.put(ClassName, TotalOccurrences);	
			AlWaysUSed.put(ClassName, Boolean.valueOf(AlwaysUsed));	
		}
        
        //Add inheritance relations
        for(String childClass : inheritance.keySet()){
            String from = "\""+childClass +"\"";
            dotGraphClasses.add(from);
            String to = "\""+inheritance.get(childClass)+"\"";
            if(!includeLibraryClasses){
                if(!allClassNames.contains(inheritance.get(childClass)))
                    continue;
            }
            dotGraphClasses.add(to);
            dotGraph.append(from+ " -> "+to+"[arrowhead = onormal];\n");
        }

        //Add associations
        for(String cls : associations.keySet()){
            Set<String> fields = associations.get(cls);
            for(String field : fields) {
                String from = "\""+cls +"\"";
                dotGraphClasses.add(from);
                String to = "\""+field+"\"";
                if(!includeLibraryClasses){
                    if(!allClassNames.contains(field))
                        continue;
                }
                dotGraphClasses.add(to);
                dotGraph.append(from + " -> " +to + "[arrowhead = diamond];\n");
            }
        }
        
        //Add all other Classes with no super classes
        for(String NR : NoRelation) {
        		String NRString = "\""+NR +"\"";
        		dotGraphClasses.add(NRString);
        }
        
        
        // to give the classes different sizes between 0 and 5, we first create list of different total occurrence numbers
        // then we sort that list
        // then we divide 5 to the size of that list
        // now for every class we get his total occurrence from the classes csv file, then we get the index of the sorted list of total occurrence numbers
        // because the list is sorted, multiplying the index of the value of dividing 5 to size of total occurrence list will give numbers between 0 and 5 depending the total occurrence
        // and we check the always used column for the color.
        List<Integer> lList = new ArrayList<Integer>();
        for (String st : TotalOccur.keySet()) {
        		lList.add(TotalOccur.get(st));
        }
        Collections.sort(lList);
        double Y = lList.size();
        double x = 5/Y;
        for(String node : dotGraphClasses){
        		String STest = node.replaceAll("^\"|\"$", "");
	        	if (AlWaysUSed.get(STest) != null) {	
	        		int Index = lList.indexOf(TotalOccur.get(STest));
	        		Index = Index+1;
	        		double wh = x * Index;
	        		if (AlWaysUSed.get(STest) == true) {
	        			dotGraph.append(node+ "[shape = box, style=filled,color=darksalmon, width="+wh+",height="+wh+"];\n");
	        		}
	        		else {
	        			dotGraph.append(node+ "[shape = box, style=filled, width="+wh+",height="+wh+"];\n");
	        		}
	        }
	        	
        }

        dotGraph.append("}");
        fw.write(dotGraph.toString());
        fw.flush();
        fw.close();
    }

    
    
    public static void main(String[] args) throws IOException{
    	new ClassDiagram("../assertj-core/target/classes/", false).writeDot(new File("../dataFiles/3B_ClassDiagram/2- ImportantClasses/classDiagramMostImportantClasses.dot"));
    }
}

