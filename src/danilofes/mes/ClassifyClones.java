package danilofes.mes;

import static danilofes.mes.CloneClassification.falsePositive;
import static danilofes.mes.CloneClassification.codeClone;
import static danilofes.mes.CloneClassification.harmfulClone;

import java.util.Random;

public class ClassifyClones {

	static Random RAND = new Random(82734710092134750L);
	private static int nextFilePair() {
		return RAND.nextInt(19800) + 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(nextFilePair());
		}
		
		//main/com/sydle/ses/workflow/web/presentation/core/connector/action/ConnectorList.java
		//main/com/sydle/ses/workflow/web/presentation/core/goal/action/GoalCreate.java
		falsePositive(10544).
		cp("28-34 -> 21-27", "cpd", 109296).
		cp("28-34 -> 21-27", "cpd", 96831);
		// Apenas as assinaturas dos métodos são iguais 

		//main/com/sydle/ses/workflow/business/core/common/filter/LaneFilter.java
		//main/com/sydle/ses/workflow/business/core/gadget/groupby/LaneGroupBy.java
		codeClone(5673).customization().specialize().
		cp("28-30 -> 30-32", "cpd", 114136). 
		cp("28-39 -> 30-41", "cpd", 72523).
		cp("28-31 -> 30-33", "simian", 60047).
		cp("60-72 -> 66-78", "cpd", 88303).
		cp("60-74 -> 66-80", "cpd", 82691);
		// Os joins de ambas as classes são os mesmos, existindo trechos idênticos.
		// Não é possível reutilizar o código via herança pois possuem superclasses distintas.
		falsePositive(5673).
		cp("66-68 -> 126-128", "simian", 54274);
		// Apenas anotações e assinaturas iguais
		
		//main/com/sydle/ses/workflow/web/presentation/core/process/activity/action/ActivityAssignmentUpdate.java
		//web/com/sydle/dataobject/ui/web/domain/action/DomainList.java
		falsePositive(15214).
		cp("18-24 -> 30-36", "cpd", 111865);
		// Apenas as assinaturas dos métodos são iguais
		
		//main/com/sydle/ses/workflow/web/presentation/core/indicator/action/IndicatorProcessRoleCreate.java
		//web/com/sydle/dataobject/ui/web/definition/action/DefinitionDelete.java
		falsePositive(12479).
		cp("20-26 -> 21-27", "cpd", 108357);
		// Apenas as assinaturas dos métodos são iguais
		
		//main/com/sydle/ses/workflow/business/core/common/filter/ProcessInstanceFinishDateFilter.java
		//main/com/sydle/ses/workflow/business/core/common/filter/ProcessInstanceLastUpdateDateFilter.java
		harmfulClone(5694).templating().parameterized().
		cp("35-43 -> 35-43", "cpd", 74711).                                                 
		cp("41-43 -> 41-43", "simian", 51881).
		cp("45-49 -> 44-48", "cpd", 89054).
		cp("46-50 -> 45-49", "simian", 64757).
		cp("48-50 -> 47-49", "simian", 51277);
		// As duas classes são filtros de intervalos de datas quase idênticos, mudando apenas por pequenos
		// parâmetros. É possível reutilizar código via herança facilmente.
		
		//8173
		//main/com/sydle/ses/workflow/dao/core/message/WflMessageInstance.java
		//main/com/sydle/ses/workflow/dao/core/process/WflSubprocess.java
		codeClone(8173).exactMatch().snippet().
		cp("109-136 -> 119-146", "cpd", 86235).
		cp("109-137 -> 119-147", "cpd", 79675).
		cp("109-139 -> 119-149", "cpd", 77263).
		cp("109-139 -> 119-149", "cpd", 76876).
		cp("110-137 -> 120-147", "cpd", 82989).
		cp("110-139 -> 120-149", "cpd", 81580).
		cp("116-124 -> 126-134", "simian", 71743).
		cp("125-130 -> 135-140", "simian", 66369);
		// Métodos isInsertable, isUpdatable, isDeletable repetidos. Embora
		// eles sejam duplicados, é um código trivial demais e não atrelado 
		// para justificar o reúso

		//main/com/sydle/ses/common/dao/usercontrol/SesUserRoleConfig.java
		//main/com/sydle/ses/workflow/dao/core/processinstance/WflProcessingItem.java
		codeClone(5424).exactMatch().snippet().
		cp("98-126 -> 224-252", "cpd", 80905).
		cp("98-128 -> 224-254", "cpd", 77933).
		cp("99-126 -> 225-252", "cpd", 83770).
		cp("99-128 -> 225-254", "cpd", 82317).
		cp("105-113 -> 231-239", "simian", 70432).
		cp("114-119 -> 240-245", "simian", 67708);
		// Métodos isInsertable, isUpdatable, isDeletable repetidos. Embora
		// eles sejam duplicados, é um código trivial demais e não atrelado 
		// para justificar o reúso
		

		//main/com/sydle/ses/common/dao/enterprise/SesDomain.java
		//main/com/sydle/ses/workflow/dao/core/processinstance/WflMessageEventInstance.java
		codeClone(4247).exactMatch().snippet().
		cp("78-106 -> 89-117", "cpd", 80231).
		cp("85-93 -> 96-104", "simian", 71128).
		cp("94-99 -> 105-110", "simian", 66999);
		// Métodos isInsertable, isUpdatable, isDeletable repetidos. Embora
		// eles sejam duplicados, é um código trivial demais e não atrelado 
		// para justificar o reúso
		
		//main/com/sydle/ses/common/dao/clazz/SesInterface.java|
		//main/com/sydle/ses/workflow/dao/core/processinstance/WflProcessInstance.java
		codeClone(4123).exactMatch().snippet().
		cp("81-91 -> 270-280", "clonedigger", 118079);
		// Métodos isInsertable, isUpdatable, isDeletable repetidos. O estranho é que o
		// clone digger marcou as linhas de um jeito estranho.

		//17737
		//web/com/sydle/dataobject/ui/web/instance/action/FileDownload.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/UserData.java                                                              
		falsePositive(17737).
		cp("32-34 -> 49-51", "simian", 57694);
		// Assinatura action
		codeClone(17737).exactMatch().snippet().
		cp("66-68 -> 35-37", "simian", 50306);
		// Método getPerm retornando explicit
		
		//main/com/sydle/dataobject/instance/MongoDBInstance.java
		//|main/com/sydle/dataobject/ui/utils/json/FileBean.java
		codeClone(303).customization().specialize().
		cp("371-374 -> 63-66", "simian", 61187);
		// As linhas inicias da implementação de hashCode são iguais.
		
		//19053
		//web/com/sydle/ses/common/web/usercontrol/group/action/GroupData.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/UserData.java
		codeClone(19053).exactMatch().snippet().
		cp("26-48 -> 28-50", "cpd", 73860).
		cp("26-49 -> 28-51", "cpd", 73367).
		cp("31-46 -> 35-50", "cpd", 75471).
		cp("31-47 -> 35-51", "cpd", 74403).
		cp("31-36 -> 35-40", "simian", 65666).
		cp("31-35 -> 35-39", "simian", 63813).
		cp("31-34 -> 35-38", "simian", 62383).
		cp("31-33 -> 35-37", "simian", 50874).
		cp("33-41 -> 37-45", "cpd", 101916).
		cp("33-46 -> 37-50", "cpd", 94265).
		cp("33-41 -> 37-45", "cpd", 89902).
		cp("39-41 -> 49-51", "simian", 58493);
		// Método getPerm retornando explicit. Algumas marcações do cpd estão estranhas pois
		// pegam o início do execute. 
		codeClone(19053).templating().parameterized().
		cp("42-51 -> 53-62", "clonedigger", 119002);
		// O código repetido é uma solução geral para recuperar o registro do banco
		// via parâmetro da action, onde apenas alguns tipos são alterados. É possível
		// reutilizar, mas o ganho não seria tão grande.

		//main/com/sydle/ses/workflow/web/presentation/core/goal/action/GoalUpdate.java
		//web/com/sydle/dataobject/ui/web/namespace/action/NamespaceList.java
		falsePositive(11107).
		cp("26-32 -> 24-30", "cpd", 109338);
		// Assinatura action
		
		//main/com/sydle/ses/common/dao/attribute/SesAttributeDataType.java
		//main/com/sydle/ses/common/dao/attribute/SesAttributeSet.java
		falsePositive(3090).
		cp("120-132 -> 56-68", "clonedigger", 117134);
		// Pares de métodos get e set com mesma estrutura, mas totalmente não
		// relacionados.
		
		//main/com/sydle/ses/workflow/web/presentation/common/action/UserSettingsSave.java                                                
		//main/com/sydle/ses/workflow/web/presentation/core/processinstance/action/ProcessInstancePrint.java
		falsePositive(9705).
		cp("36-38 -> 51-53", "simian", 56821);
		// Assinatura da action 
		
		//main/com/sydle/ses/workflow/business/core/goal/GoalVersionTesterValidator.java
		//main/com/sydle/ses/workflow/business/core/indicator/IndicatorProcessRoleValidator.java                                          
		harmfulClone(6369).exactMatch().crossCutting().
		cp("35-42 -> 85-92", "cpd", 91991).
		cp("35-42 -> 60-67", "cpd", 91990).
		cp("75-82 -> 85-92", "cpd", 92029).
		cp("75-82 -> 60-67", "cpd", 92028).
		cp("75-84 -> 85-94", "cpd", 90547);
		// Bloco catch idêntico, em vários locais, exibindo a mesma mensagem e
		// registrando no log. O código poderia ser centralizado.
		
		//13715
		//main/com/sydle/ses/workflow/web/presentation/core/process/action/AttachedEventCreate.java
		//main/com/sydle/ses/workflow/web/presentation/dashboard/gadget/action/GoalBarGadgetData.java
		falsePositive(13715).
		cp("20-26 -> 36-42", "cpd", 111411);
		// Assinatura da action 
		
		//main/com/sydle/ses/workflow/web/presentation/core/process/role/action/ProcessRoleList.java
		//main/com/sydle/ses/workflow/web/presentation/core/processinstance/action/ProcessInstanceSubjectChange.java                      
		falsePositive(15898).
		cp("23-29 -> 20-26", "cpd", 111796).                                                          
		cp("23-33 -> 20-30", "cpd", 103721).                                                          
		cp("23-29 -> 20-26", "cpd", 98593).                                                           
		cp("23-29 -> 20-26", "cpd", 93123).                                                           
		cp("28-30 -> 24-26", "cpd", 114836);
		// Assinatura da action e uma linha em comum.
		
		//main/com/sydle/ses/workflow/business/core/graphicalelement/activity/SubprocessValidator.java
		//main/com/sydle/ses/workflow/business/core/group/ProcessGroupValidator.java
		falsePositive(6658).
		cp("30-32 -> 42-44", "simian", 51713).                                                        
		cp("30-32 -> 69-71", "simian", 51712).                                                        
		cp("55-57 -> 42-44", "simian", 51694).                                                        
		cp("55-57 -> 69-71", "simian", 51693);
		// O código marcado é muito pequeno, atribuição e retorno de booleano.
		// Logo acima do mesmo existe um trecho duplicado de catch idêntico.
		
		//main/com/sydle/ses/workflow/business/integration/process/dto/ErrorEndEvent.java
		//main/com/sydle/ses/workflow/business/integration/process/dto/SignalIntermediateThrowEvent.java
		codeClone(7292).templating().languageIdioms().
		cp("31-37 -> 13-19", "cpd", 99724).
		cp("31-38 -> 13-20", "cpd", 92800).
		cp("31-35 -> 13-17", "simian", 64007).
		cp("31-34 -> 13-16", "simian", 61155).
		cp("31-33 -> 13-15", "simian", 59941);
		// Método accept do padrão visitor em comum.
	}


}
