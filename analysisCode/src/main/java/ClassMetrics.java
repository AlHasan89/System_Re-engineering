import dependenceAnalysis.util.cfg.CFGExtractor;
import dependenceAnalysis.util.cfg.Graph;
import dependenceAnalysis.util.cfg.Node;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import dependenceAnalysis.analysis.ProgramDependenceGraph;

/**
 * This class will write a single CSV file of metrics for a class.
 *
 *
 * Created by neilwalkinshaw on 24/10/2017.
 */
public class ClassMetrics {


    /**
     * First argument is the class name, e.g. /java/awt/geom/Area.class"
     * These seond argument is the name of the target csv file, e.v. "classMetrics.csv"
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	
    		File dir = new File("../assertj-core/target/classes/");
        List<Class<?>> classes = processDirectory(dir,"");
        
        //Set up the CSV Printer for all classes Metrics
        FileWriter fwClassMetrics = new FileWriter("../dataFiles/2A_ClassesAndMethodsMetrics/ClassesMetrics.csv");
        CSVPrinter csvPrinterClassMetrics = new CSVPrinter(fwClassMetrics, CSVFormat.EXCEL);
        List<String> recordClassMetrics = new ArrayList<String>();
        recordClassMetrics.add("Class");
        recordClassMetrics.add("LOC");
        recordClassMetrics.add("Wheighted Method Count");
        csvPrinterClassMetrics.printRecord(recordClassMetrics);
        
        
        //Set up the CSV Printer for all classes Methods
        FileWriter fwAllClasses = new FileWriter("../dataFiles/2A_ClassesAndMethodsMetrics/AllClassesMethods.csv");
        CSVPrinter csvPrinterAllClasses = new CSVPrinter(fwAllClasses, CSVFormat.EXCEL);
        List<String> recordAllClasses = new ArrayList<String>();
        recordAllClasses.add("Method");
        recordAllClasses.add("Description");
        recordAllClasses.add("Nodes");
        recordAllClasses.add("Cyclomatic Complexity");
        csvPrinterAllClasses.printRecord(recordAllClasses);
        
      //Set up the CSV Printer for Slice-Based Metrics
        FileWriter fwSliceMetrics = new FileWriter("../dataFiles/2B_SliceBasedMetrics/TightnessAndOverlap.csv");
        CSVPrinter csvPrinterSliceMetrics = new CSVPrinter(fwSliceMetrics, CSVFormat.EXCEL);
        List<String> recordSliceMetrics = new ArrayList<String>();
        recordSliceMetrics.add("Class");
        recordSliceMetrics.add("Method");
        recordSliceMetrics.add("Tightness");
        recordSliceMetrics.add("Overlap");
        csvPrinterSliceMetrics.printRecord(recordSliceMetrics);
        
        for (Class c : classes) {
        		int ClassCC = 0;
        		recordClassMetrics = new ArrayList<String>();
        		
        	    String path = c.getName().replaceAll("\\.", "\\/");
        	    path = "/"+path+".class";
	        
	        //Read in the class given in the argument to a ClassNode.
	        ClassNode cn = new ClassNode(Opcodes.ASM4);
	        InputStream in=CFGExtractor.class.getResourceAsStream(path);
	        ClassReader classReader=new ClassReader(in);
	        classReader.accept(cn, 0);
	        
	        String ClassName = cn.name.split("/")[cn.name.split("/").length-1];
	        
	        FileReader input = new FileReader("../assertj-core/target/classes/"+path);
	        LineNumberReader count = new LineNumberReader(input);
	        while (count.readLine() != null) {}
	        
	        
	        //Set up the CSV Printer for each class Methods
	        FileWriter fw = new FileWriter("../dataFiles/2A_ClassesAndMethodsMetrics/eachClassMethods/"+ ClassName + ".csv");
	        CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.EXCEL);
	        List<String> record = new ArrayList<String>();
	        
	        
	        record.add("Method");
	        record.add("Description");
	        record.add("Nodes");
	        record.add("Cyclomatic Complexity");
	        csvPrinter.printRecord(record);
	
	        for(MethodNode mn : (List<MethodNode>)cn.methods){
	            record = new ArrayList<String>();
	            int numNodes = -1;
	            int cyclomaticComplexity = -1; // both values default to -1 if they cannot be computed.
	            try {
	                Graph cfg = CFGExtractor.getCFG(cn.name, mn);
	                numNodes = getNodeCount(cfg);
	                cyclomaticComplexity = getCyclomaticComplexity(cfg);
	
	            } catch (AnalyzerException e) {
	                e.printStackTrace();
	            }
	
	            String MethodRealName = null;
	            	if (mn.name.equals("<init>")) {
	            		MethodRealName = ClassName;
	            	}
	            	else
	            		MethodRealName = mn.name;
	            //Write the method details and metrics to the CSV record.
	            record.add(cn.name+"."+MethodRealName); //Add method signature in first column.
	            record.add(mn.desc);
	            record.add(Integer.toString(numNodes));
	            record.add(Integer.toString(cyclomaticComplexity));
	            csvPrinter.printRecord(record);
	            csvPrinterAllClasses.printRecord(record);
	            
	            ClassCC = ClassCC + cyclomaticComplexity;
	            
	            if (numNodes > 100) {
	            		recordSliceMetrics = new ArrayList<String>();
	            		
		            MethodNode target = null;
		            target = mn;
		            ProgramDependenceGraph PDG = new ProgramDependenceGraph(cn, target);
		            Graph PD = PDG.computeResult();
		            double tightness = PDG.computeTightness();
		            double Overlap = PDG.computeOverlap();
		            recordSliceMetrics.add(cn.name);
	            		recordSliceMetrics.add(mn.name);
	            		recordSliceMetrics.add(Double.toString(tightness));
	            		recordSliceMetrics.add(Double.toString(Overlap));
	            		csvPrinterSliceMetrics.printRecord(recordSliceMetrics);
		        }
	        }
	        csvPrinter.close();
	        
	        recordClassMetrics.add(cn.name);
	        recordClassMetrics.add(Integer.toString(count.getLineNumber() + 1));
            recordClassMetrics.add(Integer.toString(ClassCC));
            csvPrinterClassMetrics.printRecord(recordClassMetrics);
            count.close();
        }
        csvPrinterAllClasses.close();
        csvPrinterClassMetrics.close();
        csvPrinterSliceMetrics.close();
    }

    /**
     * Returns the number of nodes in the CFG.
     * @param cfg
     * @return
     */
    private static int getNodeCount(Graph cfg){
        return cfg.getNodes().size();
    }

    /**
     * Returns the Cyclomatic Complexity by counting the number of branches and adding 1.
     * @param cfg
     * @return
     */
    private static int getCyclomaticComplexity(Graph cfg){
        int branchCount = 0;
        for(Node n : cfg.getNodes()){
            if(cfg.getSuccessors(n).size()>1){
                branchCount ++;
            }
        }
        return branchCount + 1;
    }

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

}
