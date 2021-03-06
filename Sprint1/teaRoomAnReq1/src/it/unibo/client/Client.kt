/* Generated by AN DISI Unibo */ 
package it.unibo.client

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Client ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var ID_client = ""
				val Order = "pesca"
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						discardMessages = false
						println("client || START")
						updateResourceRep( "START"  
						)
					}
					 transition( edgeName="goto",targetState="ringBell", cond=doswitch() )
				}	 
				state("ringBell") { //this:State
					action { //it:State
						println("client || Ring the bell")
						updateResourceRep( "Ring"  
						)
						println("client 	|| wait Enter - to Ring the Bell)")
						 readLine()  
						request("clientRingEntryRequest", "clientRingEntryRequest(entrare)" ,"smartbell" )  
					}
					 transition(edgeName="t10",targetState="simulate",cond=whenReply("clientRingEntryReply"))
				}	 
				state("simulate") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("clientRingEntryReply(ID)"), Term.createTerm("clientRingEntryReply(ID)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("client 	|| Mio ID = ${payloadArg(0)}")
								ID_client = payloadArg(0).toString() 
						}
						println("client 	|| wait Enter - to Ask for Order)")
						 readLine()  
						println("client || Ready to order")
						updateResourceRep( "OrderReady"  
						)
						forward("clientOrderReady", "clientOrderReady($ID_client)" ,"waiter" ) 
						println("client 	|| wait Enter - to Order)")
						 readLine()  
						println("client || Sending the order")
						updateResourceRep( "Ordering"  
						)
						forward("clientOrder", "clientOrder($ID_client,$Order)" ,"waiter" ) 
						println("client 	|| wait Enter - to Ask to pay)")
						 readLine()  
						println("client || I want to pay")
						updateResourceRep( "Paying"  
						)
						forward("clientPaymentReady", "clientPaymentReady($ID_client)" ,"waiter" ) 
					}
					 transition( edgeName="goto",targetState="end", cond=doswitch() )
				}	 
				state("end") { //this:State
					action { //it:State
						println("client || END")
						terminate(0)
					}
				}	 
			}
		}
}
