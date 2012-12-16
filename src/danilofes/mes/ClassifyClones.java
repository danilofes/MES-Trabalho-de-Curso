package danilofes.mes;

import static danilofes.mes.CloneClassification.codeClone;
import static danilofes.mes.CloneClassification.falsePositive;
import static danilofes.mes.CloneClassification.harmfulClone;

public class ClassifyClones {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//main/com/sydle/ses/workflow/web/presentation/core/connector/action/ConnectorList.java
		//main/com/sydle/ses/workflow/web/presentation/core/goal/action/GoalCreate.java
		falsePositive(10544).
		cp("28-34 -> 21-27", "cpd", 109296).
		cp("28-34 -> 21-27", "cpd", 96831);
		// Apenas as assinaturas do execute da action 

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
		codeClone(8173).templating().apiProtocol().
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
		codeClone(5424).templating().apiProtocol().
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
		codeClone(4247).templating().apiProtocol().
		cp("78-106 -> 89-117", "cpd", 80231).
		cp("85-93 -> 96-104", "simian", 71128).
		cp("94-99 -> 105-110", "simian", 66999);
		// Métodos isInsertable, isUpdatable, isDeletable repetidos. Embora
		// eles sejam duplicados, é um código trivial demais e não atrelado 
		// para justificar o reúso
		
		//main/com/sydle/ses/common/dao/clazz/SesInterface.java|
		//main/com/sydle/ses/workflow/dao/core/processinstance/WflProcessInstance.java
		codeClone(4123).templating().apiProtocol().
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
		//main/com/sydle/dataobject/ui/utils/json/FileBean.java
		codeClone(303).customization().specialize().
		cp("371-374 -> 63-66", "simian", 61187);
		// As linhas inicias da implementação de hashCode são iguais.
		
		//web/com/sydle/ses/common/web/usercontrol/group/action/GroupData.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/UserData.java
		falsePositive(19053).
		cp("26-48 -> 28-50", "cpd", 73860).
		cp("26-49 -> 28-51", "cpd", 73367).
		cp("31-46 -> 35-50", "cpd", 75471).
		cp("31-47 -> 35-51", "cpd", 74403);
		// Assinatura do execute
		codeClone(19053).templating().apiProtocol().
		cp("31-36 -> 35-40", "simian", 65666).
		cp("31-35 -> 35-39", "simian", 63813).
		cp("31-34 -> 35-38", "simian", 62383).
		cp("31-33 -> 35-37", "simian", 50874).
		cp("33-41 -> 37-45", "cpd", 101916).
		cp("33-46 -> 37-50", "cpd", 94265).
		cp("33-41 -> 37-45", "cpd", 89902).
		cp("39-41 -> 49-51", "simian", 58493);
		// Método getPerm retornando explicit.
		codeClone(19053).templating().parameterized().
		cp("42-51 -> 53-62", "clonedigger", 119002);
		// O código repetido é uma solução geral para recuperar o registro do banco
		// via parâmetro da action, onde apenas alguns tipos são alterados. É possível
		// reutilizar, mas o ganho não seria tão grande.

		//main/com/sydle/ses/workflow/web/presentation/core/goal/action/GoalUpdate.java
		//web/com/sydle/dataobject/ui/web/namespace/action/NamespaceList.java
		falsePositive(11107).
		cp("26-32 -> 24-30", "cpd", 109338);
		// Assinatura do execute da action
		
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
		// Assinatura do execute da action 
		
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
		
		//main/com/sydle/ses/workflow/web/presentation/core/process/action/AttachedEventCreate.java
		//main/com/sydle/ses/workflow/web/presentation/dashboard/gadget/action/GoalBarGadgetData.java
		falsePositive(13715).
		cp("20-26 -> 36-42", "cpd", 111411);
		// Assinatura do execute da action 
		
		//main/com/sydle/ses/workflow/web/presentation/core/process/role/action/ProcessRoleList.java
		//main/com/sydle/ses/workflow/web/presentation/core/processinstance/action/ProcessInstanceSubjectChange.java                      
		falsePositive(15898).
		cp("23-29 -> 20-26", "cpd", 111796).                                                          
		cp("23-33 -> 20-30", "cpd", 103721).                                                          
		cp("23-29 -> 20-26", "cpd", 98593).                                                           
		cp("23-29 -> 20-26", "cpd", 93123).                                                           
		cp("28-30 -> 24-26", "cpd", 114836);
		// Assinatura do execute da action e uma linha em comum.
		
		//main/com/sydle/ses/workflow/business/core/graphicalelement/activity/SubprocessValidator.java
		//main/com/sydle/ses/workflow/business/core/group/ProcessGroupValidator.java
		harmfulClone(6658).exactMatch().crossCutting().
		cp("30-32 -> 42-44", "simian", 51713).                                                        
		cp("30-32 -> 69-71", "simian", 51712).                                                        
		cp("55-57 -> 42-44", "simian", 51694).                                                        
		cp("55-57 -> 69-71", "simian", 51693);
		// Trecho duplicado de bloco catch idêntico.
		
		//main/com/sydle/ses/workflow/business/integration/process/dto/ErrorEndEvent.java
		//main/com/sydle/ses/workflow/business/integration/process/dto/SignalIntermediateThrowEvent.java
		codeClone(7292).templating().languageIdioms().
		cp("31-37 -> 13-19", "cpd", 99724).
		cp("31-38 -> 13-20", "cpd", 92800).
		cp("31-35 -> 13-17", "simian", 64007).
		cp("31-34 -> 13-16", "simian", 61155).
		cp("31-33 -> 13-15", "simian", 59941);
		// Método accept do padrão visitor em comum.
		
		
		//main/com/sydle/ses/common/dao/attribute/SesAttribute.java
		//main/com/sydle/ses/workflow/dao/core/process/WflSequenceFlow.java
		codeClone(3069).templating().apiProtocol().
		cp("433-460 -> 138-165", "cpd", 87306).
		cp("433-461 -> 138-166", "cpd", 80967).
		cp("440-448 -> 145-153", "simian", 70364).
		cp("449-454 -> 154-159", "simian", 67777);
		// Métodos isInsertable, isUpdatable, isDeletable repetidos.
		
		//web/com/sydle/ses/common/web/usercontrol/domain/action/DomainCreate.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/ForgotPassword.java                                                        
		falsePositive(18664).
		cp("32-45 -> 28-41", "cpd", 94342);
		// Método get perm diferentes e assinatura da action
		
		//main/com/sydle/ses/common/business/query/loader/SelectQueryFragmentAbstract.java
		//main/com/sydle/ses/workflow/business/core/gadget/aggregator/ActivityInstanceCount.java                                          
		codeClone(1466).templating().apiProtocol().
		cp("14-33 -> 86-105", "cpd", 74194).
		cp("14-18 -> 86-90", "simian", 64229).
		cp("14-17 -> 86-89", "simian", 61695).
		cp("15-31 -> 87-103", "cpd", 91573);
		// Métodos addFilter, addGroupBy e addOrderBy vazios
		
		//main/com/sydle/ses/common/dao/attribute/SesAttributeInstanceValue.java
		//main/com/sydle/ses/common/dao/attribute/SesAttributeSet.java                                                                    
		codeClone(3468).templating().apiProtocol().
		cp("133-160 -> 64-91", "cpd", 87432).                                                         
		cp("133-161 -> 64-92", "cpd", 81122).                                                         
		cp("140-148 -> 71-79", "simian", 70200).                                                      
		cp("149-154 -> 80-85", "simian", 67943);
		// Métodos isInsertable, isUpdatable, isDeletable
		
		//main/com/sydle/ses/workflow/business/core/lane/LaneValidator.java
		//main/com/sydle/ses/workflow/business/core/process/ProcessVersionTesterValidator.java                                            
		harmfulClone(6941).exactMatch().crossCutting().
		cp("25-28 -> 53-56", "cpd", 103042).                                                          
		cp("25-28 -> 33-36", "cpd", 103041).                                                          
		cp("25-33 -> 53-61", "cpd", 87969).                                                           
		cp("25-41 -> 53-69", "cpd", 77042).                                                           
		cp("25-39 -> 53-67", "cpd", 75807).                                                           
		cp("26-28 -> 55-57", "cpd", 113437).                                                          
		cp("26-28 -> 34-36", "cpd", 113436).                                                          
		cp("26-33 -> 55-62", "cpd", 91874).                                                           
		cp("26-33 -> 34-41", "cpd", 91873).                                                           
		cp("26-33 -> 55-62", "cpd", 88475).                                                           
		cp("26-41 -> 55-70", "cpd", 76743).                                                           
		cp("26-31 -> 55-60", "simian", 69110).                                                        
		cp("26-30 -> 34-38", "simian", 65266).                                                        
		cp("26-30 -> 55-59", "simian", 65265).                                                        
		cp("26-28 -> 34-36", "simian", 56374).                                                        
		cp("26-28 -> 55-57", "simian", 56373).                                                        
		cp("29-31 -> 58-60", "simian", 51648).                                                        
		cp("69-71 -> 58-60", "simian", 51637).                                                        
		cp("89-91 -> 55-57", "cpd", 113456).                                                          
		cp("89-91 -> 34-36", "cpd", 113455).                                                          
		cp("89-96 -> 55-62", "cpd", 91896).                                                           
		cp("89-96 -> 34-41", "cpd", 91895).                                                           
		cp("89-102 -> 34-47", "cpd", 89279).                                                          
		cp("89-96 -> 55-62", "cpd", 88489).                                                           
		cp("89-102 -> 34-47", "cpd", 84425).                                                          
		cp("89-102 -> 34-47", "cpd", 78461).                                                          
		cp("89-93 -> 34-38", "simian", 65248).                                                        
		cp("89-93 -> 55-59", "simian", 65247).                                                        
		cp("89-91 -> 34-36", "simian", 56355).                                                        
		cp("89-91 -> 55-57", "simian", 56354).                                                        
		cp("137-139 -> 55-57", "cpd", 113474).                                                        
		cp("137-139 -> 34-36", "cpd", 113473).                                                        
		cp("137-144 -> 55-62", "cpd", 91917).                                                         
		cp("137-144 -> 34-41", "cpd", 91916).                                                         
		cp("137-144 -> 55-62", "cpd", 88502).                                                         
		cp("137-141 -> 34-38", "simian", 64922).                                                      
		cp("137-141 -> 55-59", "simian", 64921).                                                      
		cp("137-139 -> 34-36", "simian", 56335).                                                      
		cp("137-139 -> 55-57", "simian", 56334);
		// Bloco catch idêntico
		
		//main/com/sydle/ses/common/dao/attribute/SesAttributeHist.java
		//main/com/sydle/ses/workflow/dao/core/processinstance/WflProcessingItem.java                                                     
		codeClone(3302).templating().apiProtocol().
		cp("157-185 -> 224-252", "cpd", 81423).                                                       
		cp("157-187 -> 224-254", "cpd", 78126).                                                       
		cp("158-185 -> 225-252", "cpd", 83978).                                                       
		cp("158-187 -> 225-254", "cpd", 82520).                                                       
		cp("164-172 -> 231-239", "simian", 69869).                                                    
		cp("173-178 -> 240-245", "simian", 68280);
		// Métodos isInsertable, isUpdatable
		
		
		//web/com/sydle/ses/common/web/usercontrol/group/action/GroupDelete.java
		//web/com/sydle/ses/common/web/usercontrol/role/action/RoleList.java                                                              
		falsePositive(19080).
		cp("23-45 -> 24-46", "cpd", 73841).                                                           
		cp("23-46 -> 24-47", "cpd", 73348).                                                           
		cp("28-43 -> 29-44", "cpd", 75452).                                                           
		cp("28-44 -> 29-45", "cpd", 74380).
		cp("30-38 -> 31-39", "cpd", 101888).                                                          
		cp("30-43 -> 31-44", "cpd", 94237).                                                           
		cp("30-38 -> 31-39", "cpd", 89874).                                                           
		cp("36-38 -> 37-39", "simian", 58609);
		// Assinatura do execute da action
		codeClone(19080).templating().apiProtocol().
		cp("23-45 -> 24-46", "cpd", 73841).
		cp("28-33 -> 29-34", "simian", 65643).                                                        
		cp("28-32 -> 29-33", "simian", 63836).                                                        
		cp("28-31 -> 29-32", "simian", 62360).                                                        
		cp("28-30 -> 29-31", "simian", 50977);                                                        
		// getPerm idênticos
		
		//main/com/sydle/ses/workflow/dao/core/connector/WflConnectorFile.java
		//main/com/sydle/ses/workflow/dao/core/process/WflErrorEvent.java                                                                 
		codeClone(7751).templating().apiProtocol().
		cp("69-96 -> 71-98", "cpd", 86434).                                                           
		cp("69-97 -> 71-99", "cpd", 79565).                                                           
		cp("69-99 -> 71-101", "cpd", 77234).                                                          
		cp("70-97 -> 72-99", "cpd", 82955).                                                           
		cp("70-99 -> 72-101", "cpd", 81548).                                                          
		cp("76-84 -> 77-85", "simian", 71869).                                                        
		cp("85-90 -> 85-90", "simian", 66240);
		// Métodos isInsertable, isUpdatable, isDeletable
		
		//19325
		//web/com/sydle/ses/common/web/usercontrol/permission/action/PermissionCreate.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/UserSubordinateAdd.java                                                    
		codeClone(19325).templating().apiProtocol().
		cp("31-53 -> 22-44", "cpd", 74012).                                                           
		cp("31-54 -> 22-45", "cpd", 73519).                                                           
		cp("38-53 -> 27-42", "cpd", 75623).                                                           
		cp("38-54 -> 27-43", "cpd", 74606).                                                           
		cp("38-43 -> 27-32", "simian", 65853).                                                        
		cp("38-42 -> 27-31", "simian", 63626).                                                        
		cp("38-41 -> 27-30", "simian", 62570).                                                        
		cp("38-40 -> 27-29", "simian", 50371);
		// getPerm idêntico
		
		falsePositive(19325).
		cp("40-48 -> 29-37", "cpd", 102201).                                                          
		cp("40-53 -> 29-42", "cpd", 94528).                                                           
		cp("40-48 -> 29-37", "cpd", 90187).                                                           
		cp("52-54 -> 35-37", "simian", 57863);
		// Assinatura do execute.
		
		//web/com/sydle/seed/cloud/web/account/action/Index.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/UserUpdate.java                                                            
		falsePositive(18057).
		cp("23-36 -> 31-44", "cpd", 94578);
		// getPerm diferentes e assinatura da action
		
		//main/com/sydle/ses/workflow/business/core/processinstance/graphicalelement/TokenValidator.java
		//main/com/sydle/ses/workflow/business/core/processinstance/graphicalelement/activity/ActivityInstanceValidator.java              
		codeClone(7119).templating().parameterized().
		cp("50-61 -> 75-86", "clonedigger", 117598).
		cp("61-76 -> 29-44", "cpd", 99310).
		cp("62-65 -> 87-90", "simian", 62854).
		cp("62-65 -> 31-34", "simian", 62853);
		// Pequeno fragmento de validação == null parecida. Não é nocivo por não estar
		// atrelado.

		
		//web/com/sydle/ses/common/web/usercontrol/permission/action/PermissionUpdate.java
		//web/com/sydle/ses/common/web/usercontrol/user/action/ExpiredPassword.java
		falsePositive(19430).
		cp("40-53 -> 25-38", "cpd", 94492);
		// Método getPerm difernte e assinatura do execute da Action
		
		//main/com/sydle/ses/common/dao/attribute/SesAttributeSetInstanceHist.java
		//main/com/sydle/ses/common/dao/unit/SesUnit.java
		codeClone(3774).templating().apiProtocol().
		cp("109-136 -> 141-168", "cpd", 87608).
		cp("109-137 -> 141-169", "cpd", 81346).
		cp("109-139 -> 141-171", "cpd", 78068).
		cp("110-137 -> 142-169", "cpd", 83917).
		cp("110-139 -> 142-171", "cpd", 82459).
		cp("116-124 -> 148-156", "simian", 69955).
		cp("125-130 -> 157-162", "simian", 68192);
		// Métodos isInsertable, isUpdatable, isDeletable idênticos.
		
		// Apenas business

		//main/com/sydle/dashboard/business/common/config/ConfigAbstract.java
		//main/com/sydle/dashboard/business/gadget/aggregator/GadgetAggregatorAbstract.java                                               
		codeClone(1).templating().boilerPlating().
		cp("46-49 -> 39-42", "simian", 61865).
		cp("54-57 -> 31-34", "simian", 61788);
		// Apenas método getName e getId iguais. Classes implementam mesma interface
		// ConfigIf mas não herdam da mesma superclasse, dificultando reúso.
		
		
		//main/com/sydle/seed/cloud/business/integration/cielo/message/Authorization.java
		//main/com/sydle/seed/cloud/business/integration/cielo/message/OrderData.java                                                     
		codeClone(503).templating().boilerPlating().
		cp("45-54 -> 94-103", "clonedigger", 118885).
		cp("56-79 -> 105-128", "cpd", 76844).                                                         
		cp("62-71 -> 60-69", "clonedigger", 118887).                                                  
		cp("62-66 -> 111-115", "simian", 64190).                                                      
		cp("73-96 -> 71-94", "cpd", 76848).                                                           
		cp("79-83 -> 77-81", "simian", 64753).                                                        
		cp("80-90 -> 78-88", "cpd", 114336).                                                          
		cp("80-83 -> 78-81", "simian", 61200);
		// Java beans com pares set get semelhantes, mas diferentes. pares get set iguais: DateTime, value

		
		//main/com/sydle/ses/common/business/attribute/dataprovider/AttributeValueOptionBean.java
		//main/com/sydle/ses/workflow/business/integration/processinstance/dto/ProcessInstance.java                                       
		codeClone(1005).templating().boilerPlating().
		cp("29-32 -> 50-53", "simian", 60029);
		// Java beans com método getCode idêntico
		
		//main/com/sydle/ses/common/business/usercontrol/RoleBS.java
		//main/com/sydle/ses/common/business/usercontrol/RoleBS.java
		codeClone(1507).customization().specialize().
		cp("27-29 -> 63-65", "cpd", 89648);
		// Dois métodos dentro da mesma classe montam uma consulta que inicia igual
		// mas possui um filtro a mais. O código duplicado é muito pequeno e não
		// justifica um refactoring.
		
		
		//main/com/sydle/ses/common/business/widget/DateTimeBoxWidget.java
		//main/com/sydle/ses/common/business/widget/YesNoComboBoxWidget.java                                                              
		harmfulClone(2009).templating().parameterized().
		cp("18-34 -> 18-34", "clonedigger", 115854).
		cp("18-25 -> 18-25", "cpd", 76391).
		cp("23-23 -> 23-23", "cpd", 104865).
		cp("23-25 -> 23-25", "cpd", 100892).
		cp("23-23 -> 23-23", "cpd", 93866).
		cp("23-25 -> 23-25", "cpd", 91361).                                                           
		cp("23-25 -> 23-25", "cpd", 85497).                                                           
		cp("23-25 -> 23-25", "cpd", 79222).                                                           
		cp("23-25 -> 23-25", "simian", 58925).                                                        
		cp("26-32 -> 26-32", "cpd", 115648).                                                          
		cp("27-32 -> 27-32", "simian", 69056).                                                        
		cp("30-32 -> 30-32", "simian", 48219).                                                        
		cp("33-38 -> 33-38", "cpd", 86067).                                                           
		cp("33-41 -> 33-41", "cpd", 75254).                                                           
		cp("34-38 -> 34-38", "simian", 62959).                                                        
		cp("34-37 -> 34-37", "simian", 60958).                                                        
		cp("35-41 -> 35-41", "cpd", 101459);
		// Código switch para construir um objeto muito parecido, onde a única
		// diferença é o tipo construído. Usando metaprogramação seria possível
		// reutilizar o código.
		
		//main/com/sydle/ses/common/business/widget/IntegerBoxWidget.java
		//main/com/sydle/ses/common/business/widget/MultipleMoneyBoxWidget.java
		harmfulClone(2511).templating().parameterized().
		cp("18-34 -> 18-34", "clonedigger", 115929).                                                  
		cp("18-25 -> 18-25", "cpd", 76170).                                                           
		cp("23-23 -> 23-23", "cpd", 104523).                                                          
		cp("23-25 -> 23-25", "cpd", 100626).                                                          
		cp("23-23 -> 23-23", "cpd", 93641).                                                           
		cp("23-25 -> 23-25", "cpd", 91099).                                                           
		cp("23-25 -> 23-25", "cpd", 85220).                                                           
		cp("23-25 -> 23-25", "cpd", 78960).                                                           
		cp("23-25 -> 23-25", "simian", 59187).                                                        
		cp("26-32 -> 26-32", "cpd", 115460).                                                          
		cp("27-32 -> 27-32", "simian", 68868).                                                        
		cp("30-32 -> 30-32", "simian", 48606).                                                        
		cp("33-38 -> 33-38", "cpd", 85868).                                                           
		cp("33-41 -> 33-41", "cpd", 75067).                                                           
		cp("34-38 -> 34-38", "simian", 63136).                                                        
		cp("34-37 -> 34-37", "simian", 60770).                                                        
		cp("35-41 -> 35-41", "cpd", 101258);
		// Código switch para construir um objeto muito parecido, onde a única
		// diferença é o tipo construído. Usando metaprogramação seria possível
		// reutilizar o código.
		
		
		//main/com/sydle/ses/workflow/business/core/gadget/aggregator/ProcessInstanceAverageDurationDeviation.java
		//main/com/sydle/ses/workflow/dao/core/processinstance/custom/loader/queryfragment/LastUpdateDateFilterQueryFragment.java         
		codeClone(6025).templating().boilerPlating().
		cp("79-81 -> 54-56", "simian", 53484).
		cp("133-135 -> 54-56", "simian", 53452);
		// Implementação idêntica de getRequiredTables retornando array vazio.
		
		
		//main/com/sydle/ses/workflow/business/core/graphicalelement/activity/ActivityManager.java
		//main/com/sydle/ses/workflow/business/core/graphicalelement/event/timer/TimerEventManager.java
		falsePositive(6527).
		cp("90-97 -> 75-82", "cpd", 91633);
		// Apenas anotações e assinaturas de métodos iguais
		
		//main/com/sydle/ses/workflow/business/core/process/SLAConfigValidator.java
		//main/com/sydle/ses/workflow/business/core/process/role/GraphicalElementRoleValidator.java                                       
		harmfulClone(7029).exactMatch().crossCutting().
		cp("55-58 -> 58-61", "cpd", 103222).                                                          
		cp("56-58 -> 59-61", "cpd", 113855).                                                          
		cp("56-58 -> 59-61", "simian", 55953);
		// Bloco catch igual, embora apenas duas linhas de código poderia estar
		// centralizado.
		
		
		//main/com/sydle/ses/workflow/business/integration/process/dto/ScriptTask.java
		//main/com/sydle/ses/workflow/business/integration/process/dto/TerminateEndEvent.java                                             
		harmfulClone(7531).templating().languageIdioms().
		cp("16-22 -> 13-19", "cpd", 99816).                                                           
		cp("16-23 -> 13-20", "cpd", 92890).                                                           
		cp("16-20 -> 13-17", "simian", 64137).                                                        
		cp("16-19 -> 13-16", "simian", 61179).                                                        
		cp("16-18 -> 13-15", "simian", 59719);
		// Método accept do padrão visitor em comum.
		
		//main/com/sydle/ses/workflow/business/core/graphicalelement/activity/UserTaskValidator.java
		//main/com/sydle/ses/workflow/business/core/graphicalelement/gateway/ComplexGatewayValidator.java                                 
		codeClone(6686).templating().parameterized().
		cp("81-83 -> 52-54", "simian", 51734);
		// Pequeno trecho com estrutura parecida, alterando apenas alguns tipos e constantes.
		codeClone(6686).exactMatch().snippet().
		cp("108-167 -> 97-156", "cpd", 72480).                                                        
		cp("117-142 -> 106-131", "clonedigger", 115751).
		cp("117-143 -> 106-132", "simian", 72476).                                              
		cp("129-134 -> 133-138", "cpd", 102830).                                                      
		cp("129-134 -> 122-127", "cpd", 102829).                                                      
		cp("140-145 -> 133-138", "cpd", 102833).                                                      
		cp("140-145 -> 122-127", "cpd", 102832);
		// Método validateExplicitAnotherElement duplicado. 
		
		CloneClassification.printResults();
		
	}


}
