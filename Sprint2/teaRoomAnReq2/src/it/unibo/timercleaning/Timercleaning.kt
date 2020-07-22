/* Generated by AN DISI Unibo */ 
package it.unibo.timercleaning

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Timercleaning ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var TimerTime      = 0L
				var StartTime     = 0L    
				var Duration      = 0L    
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("timercleaning		|| START")
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						println("timercleaning		|| waitCmd")
					}
					 transition(edgeName="t032",targetState="startTimer",cond=whenDispatch("startTimerCleaning"))
				}	 
				state("startTimer") { //this:State
					action { //it:State
						println("timercleaning		|| startTimer")
						if( checkMsgContent( Term.createTerm("startTimerCleaning(Timer)"), Term.createTerm("startTimerCleaning(Timer)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 TimerTime = payloadArg(0).toLong()  
								StartTime = getCurrentTime()
						}
						stateTimer = TimerActor("timer_startTimer", 
							scope, context!!, "local_tout_timercleaning_startTimer", TimerTime )
					}
					 transition(edgeName="t133",targetState="timeFinished",cond=whenTimeout("local_tout_timercleaning_startTimer"))   
					transition(edgeName="t134",targetState="stopTimer",cond=whenRequest("stopTimerCleaningReq"))
				}	 
				state("timeFinished") { //this:State
					action { //it:State
						forward("timerFinishedCorrectly", "timerFinishedCorrectly(P)" ,"waiterengine" ) 
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("stopTimer") { //this:State
					action { //it:State
						Duration = getDuration(StartTime)
						answer("stopTimerCleaningReq", "stopTimerCleaningReply", "stopTimerCleaningReply($Duration)"   )  
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
			}
		}
}
