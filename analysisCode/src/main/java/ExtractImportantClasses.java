import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class ExtractImportantClasses {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Reader classesCSV = new FileReader("../dataFiles/2D_DynamicAnalysisImportantClasses/ClassesAndMethodsCSVFiles/classes.csv");
        Iterable<CSVRecord> classesCSVIterable = CSVFormat.EXCEL.parse(classesCSV);
        
        Set<String> ClassesInTraces = new HashSet<String>();
        Set<String> ClassesInTracesForDupilcation = new HashSet<String>();
        for (CSVRecord rec : classesCSVIterable) {
        		if (rec.get(0).equals("Name")) continue;
        		ClassesInTraces.add(rec.get(0).replaceAll("\\s+",""));
        		String name = rec.get(0).replaceAll("\\s+","").split("\\.")[rec.get(0).replaceAll("\\s+","").split("\\.").length-1];
        		ClassesInTracesForDupilcation.add(name);
        }
        
        Reader methodsCSV = new FileReader("../dataFiles/2D_DynamicAnalysisImportantClasses/ClassesAndMethodsCSVFiles/methods.csv");
        Iterable<CSVRecord> methodsCSVIterable = CSVFormat.EXCEL.parse(methodsCSV);
        
        Set<String> MethodsInTraces = new HashSet<String>();
        for (CSVRecord rec : methodsCSVIterable) {
        		MethodsInTraces.add(rec.get(0).replaceAll("\\s+",""));
        }
        
        Reader ClassesMethods = new FileReader("../dataFiles/3A_MetricsAndRelations/ClassesMetricsLOCAndWMC/AllClasses/AllClassesMethods.csv");
        Iterable<CSVRecord> ClassesMethodsCSVIterable = CSVFormat.EXCEL.parse(ClassesMethods);
        
        FileWriter fw = new FileWriter("../dataFiles/3A_MetricsAndRelations/ClassesMetricsLOCAndWMC/ImportantClasses/ImportantClassesMethods.csv");
        CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.EXCEL);
        
        List<String> record = new ArrayList<String>();
        record.add("Method");
        record.add("Description");
        record.add("Nodes");
        record.add("Cyclomatic Complexity");
        csvPrinter.printRecord(record);
        for (CSVRecord rec1 : ClassesMethodsCSVIterable) {
        		if (rec1.get(0).equals("Method")) continue;
    			String name = rec1.get(0).split("\\.")[1];
    			if (MethodsInTraces.contains(name)) {
    				record = new ArrayList<String>();
    				record.add(rec1.get(0));
    		        record.add(rec1.get(1));
    		        record.add(rec1.get(2));
    		        record.add(rec1.get(3));
    		        csvPrinter.printRecord(record);
    			}
        	}
        csvPrinter.close();
        
        Reader ClassesMetrics = new FileReader("../dataFiles/3A_MetricsAndRelations/ClassesMetricsLOCAndWMC/AllClasses/ClassesMetrics.csv");
        Iterable<CSVRecord> ClassesMetricsCSVIterable = CSVFormat.EXCEL.parse(ClassesMetrics);
        
        FileWriter fwClasses = new FileWriter("../dataFiles/3A_MetricsAndRelations/ClassesMetricsLOCAndWMC/ImportantClasses/ImportantClassesMetrics.csv");
        CSVPrinter csvPrinterClasses = new CSVPrinter(fwClasses, CSVFormat.EXCEL);
        
        List<String> Classesrecord = new ArrayList<String>();
        Classesrecord.add("Name");
        Classesrecord.add("LOC");
        Classesrecord.add("Wheighted Method Count");
        csvPrinterClasses.printRecord(Classesrecord);
        for (CSVRecord rec1 : ClassesMetricsCSVIterable) {
        		if (rec1.get(0).equals("Class")) continue;
        		String name = rec1.get(0).replaceAll("\\/", "\\.");
        		if (ClassesInTraces.contains(name)) {
        			Classesrecord = new ArrayList<String>();
        			Classesrecord.add(rec1.get(0));
        	        Classesrecord.add(rec1.get(1));
        	        Classesrecord.add(rec1.get(2));
        	        csvPrinterClasses.printRecord(Classesrecord);
        		}
        }
        csvPrinterClasses.close();
        
        
        Reader RepositoryLog = new FileReader("../dataFiles/3A_MetricsAndRelations/RepositoryLog/AllChanges/RepositoryLog.csv");
        Iterable<CSVRecord> RepositoryLogCSVIterable = CSVFormat.EXCEL.parse(RepositoryLog);
        
        FileWriter fwRepositoryLog = new FileWriter("../dataFiles/3A_MetricsAndRelations/RepositoryLog/ImportantChanges/ImportantRepositoryLogFiles.csv");
        CSVPrinter csvPrinterRepositoryLog = new CSVPrinter(fwRepositoryLog, CSVFormat.EXCEL);
        
        List<String> RepositoryLogrecord = new ArrayList<String>();
        RepositoryLogrecord.add("Timestamp");
        RepositoryLogrecord.add("Message");
        RepositoryLogrecord.add("Committer");
        RepositoryLogrecord.add("Added");
        RepositoryLogrecord.add("Removed");
        RepositoryLogrecord.add("File");
        csvPrinterRepositoryLog.printRecord(RepositoryLogrecord);
        for (CSVRecord rec1 : RepositoryLogCSVIterable) {
        		if (rec1.get(5).equals("File")) continue;
        		String name = rec1.get(5);
        		if (name.split("\\/").length > 1) {
        			name = name.replaceAll("src/main/java/","");
        			name = name.replaceAll("src/test/java/","");
        			name = name.replaceAll("\\.java", "");
        			name = name.replaceAll("\\/", "\\.");
        		}
        		if (ClassesInTraces.contains(name)) {
        			RepositoryLogrecord = new ArrayList<String>();
        			RepositoryLogrecord.add(rec1.get(0));
        			RepositoryLogrecord.add(rec1.get(1));
        			RepositoryLogrecord.add(rec1.get(2));
        			RepositoryLogrecord.add(rec1.get(3));
        			RepositoryLogrecord.add(rec1.get(4));
        			RepositoryLogrecord.add(rec1.get(5));
        	        csvPrinterRepositoryLog.printRecord(RepositoryLogrecord);
        		}
        }
        csvPrinterRepositoryLog.close();
	
	
    Reader CommandLineLines = new FileReader("../dataFiles/3A_MetricsAndRelations/CommandLineMetrics/AllClasses/lineCounts.csv");
    Iterable<CSVRecord> CommandLineLinesCSVIterable = CSVFormat.EXCEL.parse(CommandLineLines);
    
    FileWriter fwCommandLineLines = new FileWriter("../dataFiles/3A_MetricsAndRelations/CommandLineMetrics/ImportantClasses/ImportantlineCounts.csv");
    CSVPrinter csvPrinterCommandLineLines = new CSVPrinter(fwCommandLineLines, CSVFormat.EXCEL);
   
    List<String> CommandLineLinesRecord = new ArrayList<String>();
    
    for (CSVRecord rec1 : CommandLineLinesCSVIterable) {
		String name = rec1.get(0);
		name = name.replaceAll("./src/main/java/","");
		name = name.replaceAll("./src/test/java/","");
		name = name.replaceAll("\\.java", "");
		name = name.replaceAll("\\/", "\\.");
		if (ClassesInTraces.contains(name)) {
			CommandLineLinesRecord = new ArrayList<String>();
			CommandLineLinesRecord.add(rec1.get(0));
			CommandLineLinesRecord.add(rec1.get(1));
			csvPrinterCommandLineLines.printRecord(CommandLineLinesRecord);
			}
		}
    csvPrinterCommandLineLines.close();

    
	Reader CommandLineComments = new FileReader("../dataFiles/3A_MetricsAndRelations/CommandLineMetrics/AllClasses/commentCounts.csv");
    Iterable<CSVRecord> CommandLineCommentsCSVIterable = CSVFormat.EXCEL.parse(CommandLineComments);
    
    FileWriter fwCommandLineComments = new FileWriter("../dataFiles/3A_MetricsAndRelations/CommandLineMetrics/ImportantClasses/ImportantcommentCounts.csv");
    CSVPrinter csvPrinterCommandLineComments = new CSVPrinter(fwCommandLineComments, CSVFormat.EXCEL);
    
    List<String> CommandLineCommentsRecord = new ArrayList<String>();
    
    for (CSVRecord rec1 : CommandLineCommentsCSVIterable) {
		String name = rec1.get(0);
		name = name.replaceAll("./src/main/java/","");
		name = name.replaceAll("./src/test/java/","");
		name = name.replaceAll("\\.java", "");
		name = name.replaceAll("\\/", "\\.");
		if (ClassesInTraces.contains(name)) {
			CommandLineCommentsRecord = new ArrayList<String>();
			CommandLineCommentsRecord.add(rec1.get(0));
			CommandLineCommentsRecord.add(rec1.get(1));
			csvPrinterCommandLineComments.printRecord(CommandLineCommentsRecord);
			}
		}
    csvPrinterCommandLineComments.close();

    
    Reader CommandLineVoc = new FileReader("../dataFiles/3A_MetricsAndRelations/CommandLineMetrics/AllClasses/vocabCounts.csv");
    Iterable<CSVRecord> CommandLineVocCSVIterable = CSVFormat.EXCEL.parse(CommandLineVoc);
    
    FileWriter fwCommandLineVoc = new FileWriter("../dataFiles/3A_MetricsAndRelations/CommandLineMetrics/ImportantClasses/ImportantvocabCounts.csv");
    CSVPrinter csvPrinterCommandLineVoc = new CSVPrinter(fwCommandLineVoc, CSVFormat.EXCEL);
    
    List<String> CommandLineVocRecord = new ArrayList<String>();
    
    for (CSVRecord rec1 : CommandLineVocCSVIterable) {
		String name = rec1.get(0);
		name = name.replaceAll("./src/main/java/","");
		name = name.replaceAll("./src/test/java/","");
		name = name.replaceAll("\\.java", "");
		name = name.replaceAll("\\/", "\\.");
		if (ClassesInTraces.contains(name)) {
			CommandLineVocRecord = new ArrayList<String>();
			CommandLineVocRecord.add(rec1.get(0));
			CommandLineVocRecord.add(rec1.get(1));
			csvPrinterCommandLineVoc.printRecord(CommandLineVocRecord);
			}
		}
    csvPrinterCommandLineVoc.close();
    
    Reader DuplicationDetector = new FileReader("../dataFiles/3C_CodeDuplication/1- AllClasses/fileComparison.csv");
    Iterable<CSVRecord> DuplicationDetectorCSVIterable = CSVFormat.EXCEL.parse(DuplicationDetector);
    
    FileWriter fwDuplicationDetector = new FileWriter("../dataFiles/3C_CodeDuplication/2- ImportantClasses/ImportantfileComparison.csv");
    CSVPrinter csvPrinterDuplicationDetector = new CSVPrinter(fwDuplicationDetector, CSVFormat.EXCEL);
    
    List<String> DuplicationDetectorRecord = new ArrayList<String>();
    DuplicationDetectorRecord.add("From");
    DuplicationDetectorRecord.add("To");
    DuplicationDetectorRecord.add("Overlap");
    csvPrinterDuplicationDetector.printRecord(DuplicationDetectorRecord);
    
    for (CSVRecord rec1 : DuplicationDetectorCSVIterable) {
    		if (rec1.get(0).equals("From")) continue;
		String From = rec1.get(0);
		String To =  rec1.get(1);
		From = From.replaceAll("\\.java", "");
		To = To.replaceAll("\\.java", "");
		if ( (ClassesInTracesForDupilcation.contains(From)) && (ClassesInTracesForDupilcation.contains(To)) ){
			DuplicationDetectorRecord = new ArrayList<String>();
			DuplicationDetectorRecord.add(rec1.get(0));
			DuplicationDetectorRecord.add(rec1.get(1));
			DuplicationDetectorRecord.add(rec1.get(2));
			csvPrinterDuplicationDetector.printRecord(DuplicationDetectorRecord);
			}
		}
    csvPrinterDuplicationDetector.close();
    
	}
}
