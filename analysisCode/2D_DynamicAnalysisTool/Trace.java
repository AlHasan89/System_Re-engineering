package aspects;

import java.util.logging.*;

import org.aspectj.lang.Signature;
import org.aspectj.lang.*;
import org.aspectj.lang.reflect.*;

aspect Trace{

        private static Logger logger = Logger.getLogger("Tracing");

        public Trace(){

        try{
		// export log file as csv file
                FileHandler handler=new FileHandler("../../dataFiles/2D_DynamicAnalysisImportantClasses/TracesLogFiles/Test.csv",false);

                logger.addHandler(handler);

                handler.setFormatter(new Formatter(){

                        public String format(LogRecord record){
                                return record.getMessage()+"\n";
                                }
                                });
                        }

        catch(Exception e){}

        }
	// define the stack depth counter
	int i = 0;

	
        pointcut traceMethods() : (execution(* *(..))&& within(org.assertj..*) && !cflow(within(Trace)));

        before(): traceMethods(){
		MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
		CodeSignature codeSignature = (CodeSignature) thisJoinPoint.getSignature();

		i++;
		Object[] args = codeSignature.getParameterTypes();
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			sb.append( o );
			sb.append((i == args.length - 1) ? "" : "- ");
		}
		sb.append("]");

		Logger.getLogger("Tracing").log(
                        Level.INFO,
                        methodSignature.getDeclaringTypeName() + "  ,  " + methodSignature.getMethod().getName()
			+ "  ,  "
			+ sb
			+ "  ,  "
			+ i
                );
        }
	// the stack depth decreased when the method get the return value.
	after() returning: traceMethods(){
		i--;
	}
}
