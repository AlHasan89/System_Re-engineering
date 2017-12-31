import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class RcodeGenerator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader Test1 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/api_Assertions_assertThat_with_Character_Test.csv");
		Iterable<CSVRecord> Test1Records = CSVFormat.EXCEL.parse(Test1);
		Reader Test2 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/condition_AnyOf_matches_Test.csv");
		Iterable<CSVRecord> Test2Records = CSVFormat.EXCEL.parse(Test2);
		Reader Test3 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/data_MapEntry_toString_Test.csv");
		Iterable<CSVRecord> Test3Records = CSVFormat.EXCEL.parse(Test3);
		Reader Test4 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/description_TextDescription_constructor_Test.csv");
		Iterable<CSVRecord> Test4Records = CSVFormat.EXCEL.parse(Test4);
		Reader Test5 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/error_ShouldNotHaveDuplicates_create_Test.csv");
		Iterable<CSVRecord> Test5Records = CSVFormat.EXCEL.parse(Test5);
		Reader Test6 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/extractor_ByNameSingleExtractorTest.csv");
		Iterable<CSVRecord> Test6Records = CSVFormat.EXCEL.parse(Test6);
		Reader Test7 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/groups_Properties_extractProperty_Test.csv");
		Iterable<CSVRecord> Test7Records = CSVFormat.EXCEL.parse(Test7);
		Reader Test8 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/internal_TypeComparators_Test.csv");
		Iterable<CSVRecord> Test8Records = CSVFormat.EXCEL.parse(Test8);
		Reader Test9 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/navigation_ClassBasedNavigableIterable_Test.csv");
		Iterable<CSVRecord> Test9Records = CSVFormat.EXCEL.parse(Test9);
		Reader Test10 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/presentation_StandardRepresentation_toStringOf_Test.csv");
		Iterable<CSVRecord> Test10Records = CSVFormat.EXCEL.parse(Test10);
		Reader Test11 = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/util_Arrays_hasOnlyNullElements_Test.csv");
		Iterable<CSVRecord> Test11Records = CSVFormat.EXCEL.parse(Test11);
		
		WriteDotFile(Test1Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/api_Assertions_assertThat_with_Character_Test.csv");
		WriteDotFile(Test2Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/condition_AnyOf_matches_Test.csv");
		WriteDotFile(Test3Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/data_MapEntry_toString_Test.csv");
		WriteDotFile(Test4Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/description_TextDescription_constructor_Test.csv");
		WriteDotFile(Test5Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/error_ShouldNotHaveDuplicates_create_Test.csv");
		WriteDotFile(Test6Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/extractor_ByNameSingleExtractorTest.csv");
		WriteDotFile(Test7Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/groups_Properties_extractProperty_Test.csv");
		WriteDotFile(Test8Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/internal_TypeComparators_Test.csv");
		WriteDotFile(Test9Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/navigation_ClassBasedNavigableIterable_Test.csv");
		WriteDotFile(Test10Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/presentation_StandardRepresentation_toStringOf_Test.csv");
		WriteDotFile(Test11Records, "../../dataFiles/3A_MetricsAndRelations/ExecutionPhases/1_ExecutionPhasesByTest/util_Arrays_hasOnlyNullElements_Test.csv");
		
	}
	
	public static void WriteDotFile (Iterable<CSVRecord> X, String dir) throws IOException {
		List<String> Time = new ArrayList<String>();
		List<Integer> CallDepth =  new ArrayList<Integer>();
		// get the method name and call depth from traces log files
		for (CSVRecord rec : X) {
			String MethodName = rec.get(1);
			String STest = MethodName.replaceAll("\\s","");
			String CallDepthString = rec.get(3);
			String intNumber = CallDepthString.replaceAll("\\s","");
			Integer CallDepthInt = Integer.parseInt(intNumber);
			Time.add(STest);	
			CallDepth.add(CallDepthInt);
		}
		
		Reader methods = new FileReader("../../dataFiles/2D_DynamicAnalysisImportantClasses/ClassesAndMethodsCSVFiles/methods.csv");
		Iterable<CSVRecord> methodsRecords = CSVFormat.EXCEL.parse(methods);
		
		// get the always use cloumn for each method from methods.csv file
		HashMap<String, Boolean> AlwaysUsed =  new HashMap<String, Boolean>();
		for (CSVRecord rec : methodsRecords) {
			String MethodName = rec.get(0);
			String STest = MethodName.replaceAll("\\s","");
			boolean AlwaysUsedBool = Boolean.valueOf(rec.get(2));
			AlwaysUsed.put(STest, AlwaysUsedBool);
		}
		
		FileWriter fw = new FileWriter(dir);
		CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.EXCEL);
		
		// create new csv file with Method Namd, Call Depth and if AlwaysUsed.
		List<String> record = new ArrayList<String>();
		record.add("MethodName");
		record.add("CallDepth");
		record.add("AlwaysUsed");
		csvPrinter.printRecord(record);
		Integer MethodsExecutedNumber = Time.size();
		for (int counter = 0; counter < MethodsExecutedNumber; counter++) {
			record = new ArrayList<String>();
			record.add(counter+"."+Time.get(counter));
			record.add(CallDepth.get(counter).toString());
			record.add(AlwaysUsed.get(Time.get(counter)).toString());
			csvPrinter.printRecord(record);
		}
		csvPrinter.close();
	}

}
