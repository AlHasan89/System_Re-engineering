import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class CSVGenerator {

	public static void main(String[] args) throws IOException {
		Reader Test1 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_assertThat_with_Boolean_Test.csv");
		Reader Test2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_assertThat_with_Character_Test.csv");
		Reader Test3 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_tuple_Test.csv");
		Reader Test4 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/condition_AnyOf_matches_Test.csv");
		Reader Test5 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/data_MapEntry_toString_Test.csv");
		Reader Test6 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/description_TextDescription_constructor_Test.csv");
		Reader Test7 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/error_ShouldNotHaveDuplicates_create_Test.csv");
		Reader Test8 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/extractor_ByNameSingleExtractorTest.csv");
		Reader Test9 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/groups_Properties_extractProperty_Test.csv");
		Reader Test10 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/internal_StandardComparisonStrategy_stringContains_Test.csv");
		Reader Test11 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/internal_TypeComparators_Test.csv");
		Reader Test12 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/navigation_ClassBasedNavigableIterable_Test.csv");
		Reader Test13 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/presentation_StandardRepresentation_toStringOf_Test.csv");
		Reader Test14 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/util_Arrays_hasOnlyNullElements_Test.csv");
		
		Reader Test1_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_assertThat_with_Boolean_Test.csv");
		Reader Test2_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_assertThat_with_Character_Test.csv");
		Reader Test3_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_tuple_Test.csv");
		Reader Test4_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/condition_AnyOf_matches_Test.csv");
		Reader Test5_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/data_MapEntry_toString_Test.csv");
		Reader Test6_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/description_TextDescription_constructor_Test.csv");
		Reader Test7_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/error_ShouldNotHaveDuplicates_create_Test.csv");
		Reader Test8_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/extractor_ByNameSingleExtractorTest.csv");
		Reader Test9_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/groups_Properties_extractProperty_Test.csv");
		Reader Test10_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/internal_StandardComparisonStrategy_stringContains_Test.csv");
		Reader Test11_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/internal_TypeComparators_Test.csv");
		Reader Test12_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/navigation_ClassBasedNavigableIterable_Test.csv");
		Reader Test13_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/presentation_StandardRepresentation_toStringOf_Test.csv");
		Reader Test14_2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/util_Arrays_hasOnlyNullElements_Test.csv");
		
        Iterable<CSVRecord> Test1Records = CSVFormat.EXCEL.parse(Test1);
        Iterable<CSVRecord> Test2Records = CSVFormat.EXCEL.parse(Test2);
        Iterable<CSVRecord> Test3Records = CSVFormat.EXCEL.parse(Test3);
        Iterable<CSVRecord> Test4Records = CSVFormat.EXCEL.parse(Test4);
        Iterable<CSVRecord> Test5Records = CSVFormat.EXCEL.parse(Test5);
        Iterable<CSVRecord> Test6Records = CSVFormat.EXCEL.parse(Test6);
        Iterable<CSVRecord> Test7Records = CSVFormat.EXCEL.parse(Test7);
        Iterable<CSVRecord> Test8Records = CSVFormat.EXCEL.parse(Test8);
        Iterable<CSVRecord> Test9Records = CSVFormat.EXCEL.parse(Test9);
        Iterable<CSVRecord> Test10Records = CSVFormat.EXCEL.parse(Test10);
        Iterable<CSVRecord> Test11Records = CSVFormat.EXCEL.parse(Test11);
        Iterable<CSVRecord> Test12Records = CSVFormat.EXCEL.parse(Test12);
        Iterable<CSVRecord> Test13Records = CSVFormat.EXCEL.parse(Test13);
        Iterable<CSVRecord> Test14Records = CSVFormat.EXCEL.parse(Test14);
        
        Iterable<CSVRecord> Test1Records2 = CSVFormat.EXCEL.parse(Test1_2);
        Iterable<CSVRecord> Test2Records2 = CSVFormat.EXCEL.parse(Test2_2);
        Iterable<CSVRecord> Test3Records2 = CSVFormat.EXCEL.parse(Test3_2);
        Iterable<CSVRecord> Test4Records2 = CSVFormat.EXCEL.parse(Test4_2);
        Iterable<CSVRecord> Test5Records2 = CSVFormat.EXCEL.parse(Test5_2);
        Iterable<CSVRecord> Test6Records2 = CSVFormat.EXCEL.parse(Test6_2);
        Iterable<CSVRecord> Test7Records2 = CSVFormat.EXCEL.parse(Test7_2);
        Iterable<CSVRecord> Test8Records2 = CSVFormat.EXCEL.parse(Test8_2);
        Iterable<CSVRecord> Test9Records2 = CSVFormat.EXCEL.parse(Test9_2);
        Iterable<CSVRecord> Test10Records2 = CSVFormat.EXCEL.parse(Test10_2);
        Iterable<CSVRecord> Test11Records2 = CSVFormat.EXCEL.parse(Test11_2);
        Iterable<CSVRecord> Test12Records2 = CSVFormat.EXCEL.parse(Test12_2);
        Iterable<CSVRecord> Test13Records2 = CSVFormat.EXCEL.parse(Test13_2);
        Iterable<CSVRecord> Test14Records2 = CSVFormat.EXCEL.parse(Test14_2);
        
        
        HashMap<String, Integer> ClassesNames = new HashMap<String, Integer>();
        Set<String> AlwaysUsed1 = new HashSet<String>();
        Set<String> AlwaysUsed2 = new HashSet<String>();
        Set<String> AlwaysUsed3 = new HashSet<String>();
        Set<String> AlwaysUsed4 = new HashSet<String>();
        Set<String> AlwaysUsed5 = new HashSet<String>();
        Set<String> AlwaysUsed6 = new HashSet<String>();
        Set<String> AlwaysUsed7 = new HashSet<String>();
        Set<String> AlwaysUsed8 = new HashSet<String>();
        Set<String> AlwaysUsed9 = new HashSet<String>();
        Set<String> AlwaysUsed10 = new HashSet<String>();
        Set<String> AlwaysUsed11 = new HashSet<String>();
        Set<String> AlwaysUsed12 = new HashSet<String>();
        Set<String> AlwaysUsed13 = new HashSet<String>();
        Set<String> AlwaysUsed14 = new HashSet<String>();
        
        
        // put all classes names in set for each trace csv file
        AlwaysUsed1 = ComputesAlwaysUSed(Test1Records2, AlwaysUsed1);
        AlwaysUsed2 = ComputesAlwaysUSed(Test2Records2, AlwaysUsed2);
        AlwaysUsed3 = ComputesAlwaysUSed(Test3Records2, AlwaysUsed3);
        AlwaysUsed4 = ComputesAlwaysUSed(Test4Records2, AlwaysUsed4);
        AlwaysUsed5 = ComputesAlwaysUSed(Test5Records2, AlwaysUsed5);
        AlwaysUsed6 = ComputesAlwaysUSed(Test6Records2, AlwaysUsed6);
        AlwaysUsed7 = ComputesAlwaysUSed(Test7Records2, AlwaysUsed7);
        AlwaysUsed8 = ComputesAlwaysUSed(Test8Records2, AlwaysUsed8);
        AlwaysUsed9 = ComputesAlwaysUSed(Test9Records2, AlwaysUsed9);
        AlwaysUsed10 = ComputesAlwaysUSed(Test10Records2, AlwaysUsed10);
        AlwaysUsed11 = ComputesAlwaysUSed(Test11Records2, AlwaysUsed11);
        AlwaysUsed12 = ComputesAlwaysUSed(Test12Records2, AlwaysUsed12);
        AlwaysUsed13 = ComputesAlwaysUSed(Test13Records2, AlwaysUsed13);
        AlwaysUsed14 = ComputesAlwaysUSed(Test14Records2, AlwaysUsed14);
        
        // get the intersection of the classes names using retainAll method.
        AlwaysUsed1.retainAll(AlwaysUsed2);
        AlwaysUsed1.retainAll(AlwaysUsed3);
        AlwaysUsed1.retainAll(AlwaysUsed4);
        AlwaysUsed1.retainAll(AlwaysUsed5);
        AlwaysUsed1.retainAll(AlwaysUsed6);
        AlwaysUsed1.retainAll(AlwaysUsed7);
        AlwaysUsed1.retainAll(AlwaysUsed8);
        AlwaysUsed1.retainAll(AlwaysUsed9);
        AlwaysUsed1.retainAll(AlwaysUsed10);
        AlwaysUsed1.retainAll(AlwaysUsed11);
        AlwaysUsed1.retainAll(AlwaysUsed12);
        AlwaysUsed1.retainAll(AlwaysUsed13);
        AlwaysUsed1.retainAll(AlwaysUsed14);
        
        // get the total occurrence for each class using Compute() method
		ClassesNames = Compute(Test1Records, ClassesNames);
		ClassesNames = Compute(Test2Records, ClassesNames);
		ClassesNames = Compute(Test3Records, ClassesNames);
		ClassesNames = Compute(Test4Records, ClassesNames);
		ClassesNames = Compute(Test5Records, ClassesNames);
		ClassesNames = Compute(Test6Records, ClassesNames);
		ClassesNames = Compute(Test7Records, ClassesNames);
		ClassesNames = Compute(Test8Records, ClassesNames);
		ClassesNames = Compute(Test9Records, ClassesNames);
		ClassesNames = Compute(Test10Records, ClassesNames);
		ClassesNames = Compute(Test11Records, ClassesNames);
		ClassesNames = Compute(Test12Records, ClassesNames);
		ClassesNames = Compute(Test13Records, ClassesNames);
		ClassesNames = Compute(Test14Records, ClassesNames);
		
		
		//Set up the CSV Printer		
		FileWriter fw = new FileWriter("../../dataFiles/2D_DynamicAnalysisImportantClasses/ClassesAndMethodsCSVFiles/classes.csv");
		CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.EXCEL);
		List<String> record = new ArrayList<String>();
		record.add("Name");
		record.add("TotalOccurrences");
		record.add("AlwaysUsed");
		csvPrinter.printRecord(record);
		        
        
		for (String key : ClassesNames.keySet()) {
			record = new ArrayList<String>();
			
		    record.add(key);
	        record.add(ClassesNames.get(key).toString());
	        
	        if (AlwaysUsed1.contains(key)) {
	        		record.add("True");
	        }
	        else {
	        		record.add("False");
	        }
	        
	        
	        csvPrinter.printRecord(record);
		}
        
        csvPrinter.close();
	}
	
	
	// Compute total occurrence
	// take the first element from the csv file, which is class name
	// check if the hashmap contains that class
	// if not add it with total occurrence 1
	// if it exist get the total occurrece and increase it by 1
	public static HashMap<String, Integer> Compute(Iterable<CSVRecord> X,HashMap<String, Integer> Y){
		for (CSVRecord rec : X) {
			String ClassName = rec.get(0);
			if (Y.containsKey(ClassName)) {
				Y.put(ClassName, Y.get(ClassName) + 1);
			}
			else {
				Y.put(ClassName, 1);
			}
			
		}
		return Y;
	}
	
	// see if always used
	// take all the classes names and put them in SET
	public static Set<String> ComputesAlwaysUSed(Iterable<CSVRecord> X,Set<String> Y){
		for (CSVRecord rec : X) {
			String ClassName = rec.get(0);
				Y.add(ClassName);	
		}
		return Y;
	}
	

}
