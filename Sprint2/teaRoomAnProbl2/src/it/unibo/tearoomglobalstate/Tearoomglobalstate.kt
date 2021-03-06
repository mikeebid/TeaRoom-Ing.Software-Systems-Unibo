/* Generated by AN DISI Unibo */ 
package it.unibo.tearoomglobalstate

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Tearoomglobalstate ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				val TimeMaxStay 	= 90000L
				val TotalCleaning	= 16000L	
				var TimeLeft1 		= 0L
				var TimeLeft2 		= 0L
				var ToReturnTimer 	= 0L
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("tearoomglobalstate		|| START")
						solve("consult('tearoomstate.pl')","") //set resVar	
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("wait") { //this:State
					action { //it:State
						println("tearoomglobalstate		|| wait")
						 var StateRoom = ""  
						solve("statetearoom(waiter(SW),barman(SB),stateTable(1,ST1,T1),stateTable(2,ST2,T2),clientiAccettati(NA),clientiRifiutati(NR))","") //set resVar	
						if( currentSolution.isSuccess() ) {
										StateRoom = 
										"{\"Waiter\":"		+"\""+   	getCurSol("SW").toString()   +"\"," +
										"\"Barman\":"		+"\""+    	getCurSol("SB").toString()   +"\","  +
										"\"TABLE1\":"		+"\""+    	getCurSol("ST1").toString()   +"\","+
										"\"timerDone1\":" 	+"\""+		getCurSol("T1").toString()   +"\","  +
										"\"TABLE2\":" 		+"\""+		getCurSol("ST2").toString()   +"\"," +
										"\"timerDone2\":" 	+"\""+		getCurSol("T2").toString()   +"\","  +
										"\"ClientAccepted\":"	+"\""+	getCurSol("NA").toString()   +"\"," +
										"\"ClientRejected\":"	+"\""+	getCurSol("NR").toString()   +"\""  +"}"
						}
						else
						{}
						updateResourceRep("$StateRoom" 
						)
						println("***********************")
						println(StateRoom)
						println("***********************")
					}
					 transition(edgeName="t0130",targetState="waiterState",cond=whenDispatch("setWaiterState"))
					transition(edgeName="t0131",targetState="barmanState",cond=whenDispatch("setBarmanState"))
					transition(edgeName="t0132",targetState="orderReady",cond=whenDispatch("addOrderReady"))
					transition(edgeName="t0133",targetState="orderTaken",cond=whenDispatch("removeOrderReady"))
					transition(edgeName="t0134",targetState="clientAccepted",cond=whenDispatch("addClientAccepted"))
					transition(edgeName="t0135",targetState="clientRejected",cond=whenDispatch("addClientRejected"))
					transition(edgeName="t0136",targetState="stateTable",cond=whenDispatch("setStateTable"))
					transition(edgeName="t0137",targetState="occupyTableState",cond=whenDispatch("occupyTable"))
					transition(edgeName="t0138",targetState="timerStoppedTable",cond=whenDispatch("setTimerTableStopped"))
					transition(edgeName="t0139",targetState="returnTableToClean",cond=whenRequest("getTableToCleanReq"))
					transition(edgeName="t0140",targetState="returnFreeTable",cond=whenRequest("getFreeCleanTableReq"))
					transition(edgeName="t0141",targetState="returnTableFromId",cond=whenRequest("getTableFromIdReq"))
					transition(edgeName="t0142",targetState="returnTimeInform",cond=whenRequest("getTimerForInformReq"))
				}	 
				state("waiterState") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("setWaiterState(STATO)"), Term.createTerm("setWaiterState(STATO)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("setWaiter(${payloadArg(0)})","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("barmanState") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("setBarmanState(STATO)"), Term.createTerm("setBarmanState(STATO)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("setBarman(${payloadArg(0)})","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("orderReady") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("addOrderReady(ID)"), Term.createTerm("addOrderReady(ID)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("addOrderReady(${payloadArg(0)})","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("orderTaken") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("removeOrderReady(ID)"), Term.createTerm("removeOrderReady(ID)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("removeOrderReady(${payloadArg(0)})","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("clientAccepted") { //this:State
					action { //it:State
						solve("incAccettati","") //set resVar	
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("clientRejected") { //this:State
					action { //it:State
						solve("incRifiutati","") //set resVar	
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("stateTable") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("setStateTable(N,STATO,TIMERDONE)"), Term.createTerm("setStateTable(N,STATO,TIMERDONE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("setStateTable(${payloadArg(0)},${payloadArg(1)},${payloadArg(2)})","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("occupyTableState") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("occupyTable(N,ID)"), Term.createTerm("occupyTable(N,ID)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("occupyTable(${payloadArg(0)},${payloadArg(1)})","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("timerStoppedTable") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("setTimerTableStopped(N,TIMER)"), Term.createTerm("setTimerTableStopped(N,TIMER)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("stateTable(${payloadArg(0)},S,T)","") //set resVar	
								if( currentSolution.isSuccess() ) {
														var Stato = getCurSol("S").toString()
														var Tempo = getCurSol("T").toString().toLong() + payloadArg(1).toLong()
								solve("setStateTable(${payloadArg(0)},$Stato,$Tempo)","") //set resVar	
								}
								else
								{}
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("returnTimeInform") { //this:State
					action { //it:State
						 ToReturnTimer = 0L  
						solve("stateTable(1,S,T)","") //set resVar	
						if( currentSolution.isSuccess() ) { var Stato = getCurSol("S").toString()  
						if(  Stato == "tableDirty" || Stato == "tableCleaning" || Stato == "tableClearing" || Stato == "tableSanitizing" 
						 ){ ToReturnTimer = TotalCleaning  
						}
						}
						else
						{}
						if(  ToReturnTimer == 0L  
						 ){solve("stateTable(2,S,T)","") //set resVar	
						if( currentSolution.isSuccess() ) { var Stato = getCurSol("S").toString()  
						if(  Stato == "tableDirty" || Stato == "tableCleaning" || Stato == "tableClearing" || Stato == "tableSanitizing" 
						 ){ ToReturnTimer = TotalCleaning  
						}
						}
						else
						{}
						}
						if(  ToReturnTimer != 0L  
						 ){answer("getTimerForInformReq", "getTimerForInformReply", "getTimerForInformReply($ToReturnTimer)"   )  
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitchGuarded({ ToReturnTimer != 0L  
					}) )
					transition( edgeName="goto",targetState="askTimeLeft1", cond=doswitchGuarded({! ( ToReturnTimer != 0L  
					) }) )
				}	 
				state("askTimeLeft1") { //this:State
					action { //it:State
						request("askMaxStayTimeLeftReq", "askMaxStayTimeLeftReq(1)" ,"maxstaytime" )  
					}
					 transition(edgeName="t0143",targetState="askTimeLeft2",cond=whenReply("askMaxStayTimeLeftReply"))
				}	 
				state("askTimeLeft2") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("askMaxStayTimeLeftReply(TIMERLEFT)"), Term.createTerm("askMaxStayTimeLeftReply(TIMERLEFT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 TimeLeft1 = payloadArg(0).toLong() 
						}
						request("askMaxStayTimeLeftReq", "askMaxStayTimeLeftReq(2)" ,"maxstaytime" )  
					}
					 transition(edgeName="t0144",targetState="respondForInform",cond=whenReply("askMaxStayTimeLeftReply"))
				}	 
				state("respondForInform") { //this:State
					action { //it:State
						 ToReturnTimer = 0L  
						if( checkMsgContent( Term.createTerm("askMaxStayTimeLeftReply(TIMERLEFT)"), Term.createTerm("askMaxStayTimeLeftReply(TIMERLEFT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 TimeLeft2 = payloadArg(0).toLong() 
						}
						 ToReturnTimer = TotalCleaning  
						if(  TimeLeft1 < TimeLeft2  
						 ){ ToReturnTimer += TimeLeft1  
						}
						else
						 { ToReturnTimer += TimeLeft2  
						 }
						answer("getTimerForInformReq", "getTimerForInformReply", "getTimerForInformReply($ToReturnTimer)"   )  
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("returnTableToClean") { //this:State
					action { //it:State
						
									var ToReturn = 0
									var Stato1String = ""
									var Stato2String = ""
									var Stato1 = 0
									var Stato2 = 0	
									var Tempo1 = 0
									var Tempo2 = 0
						solve("stateTable(1,S,T)","") //set resVar	
						if( currentSolution.isSuccess() ) {
										Stato1String = getCurSol("S").toString()
										if (Stato1String == "tableDirty") Stato1 = 1
										else if (Stato1String == "tableClearing") Stato1 = 2
										else if (Stato1String == "tableSanitizing") Stato1 = 3
										else if (Stato1String == "tableCleaning") Stato1 = 4
										Tempo1 = getCurSol("T").toString().toInt()
						}
						else
						{}
						solve("stateTable(2,S,T)","") //set resVar	
						if( currentSolution.isSuccess() ) {
										Stato2String = getCurSol("S").toString()
										if (Stato2String == "tableDirty") Stato2 = 1
										else if (Stato2String == "tableClearing") Stato2 = 2
										else if (Stato2String == "tableSanitizing") Stato2 = 3
										else if (Stato2String == "tableCleaning") Stato2 = 4
										Tempo2 = getCurSol("T").toString().toInt()
						}
						else
						{}
						
									if (Stato1 + Stato2 == 0) ToReturn = 0
									else if( (Stato1 > Stato2) || (Stato1 == Stato2 && Tempo1>=Tempo2)  ) ToReturn = 1
									else ToReturn = 2
						if( ToReturn == 0 
						 ){answer("getTableToCleanReq", "getTableToCleanReply", "getTableToCleanReply(0,0,0)"   )  
						}
						if( ToReturn == 1 
						 ){answer("getTableToCleanReq", "getTableToCleanReply", "getTableToCleanReply(1,$Stato1String,$Tempo1)"   )  
						}
						if( ToReturn == 2 
						 ){answer("getTableToCleanReq", "getTableToCleanReply", "getTableToCleanReply(2,$Stato2String,$Tempo2)"   )  
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("returnFreeTable") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("getFreeCleanTableReq(P)"), Term.createTerm("getFreeCleanTableReq(P)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												var ToReturnFree = 0	
								solve("stateTable(2,tableCleaned,0)","") //set resVar	
								if( currentSolution.isSuccess() ) {
													ToReturnFree = 2	
								}
								else
								{}
								solve("stateTable(1,tableCleaned,0)","") //set resVar	
								if( currentSolution.isSuccess() ) {
													ToReturnFree = 1	
								}
								else
								{}
								answer("getFreeCleanTableReq", "getFreeCleanTableReply", "getFreeCleanTableReply($ToReturnFree)"   )  
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
				state("returnTableFromId") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("getTableFromIdReq(ID)"), Term.createTerm("getTableFromIdReq(ID)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var Table = "0" 
								solve("stateTable(N,tableOccupied(${payloadArg(0)}),0)","") //set resVar	
								if( currentSolution.isSuccess() ) {
													Table = getCurSol("N").toString()
								}
								else
								{}
								answer("getTableFromIdReq", "getTableFromIdReply", "getTableFromIdReply($Table)"   )  
						}
					}
					 transition( edgeName="goto",targetState="wait", cond=doswitch() )
				}	 
			}
		}
}
