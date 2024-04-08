/* Generated by AN DISI Unibo */ 
package it.unibo.display

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Display ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val d = utils.DisplayObj.create()  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handleout",cond=whenDispatch("out"))
				}	 
				state("handleout") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("out(TERM)"), Term.createTerm("out(TERM)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 val OutMsg = payloadArg(0)  
								 d.write("$OutMsg")  
								updateResourceRep( OutMsg  
								)
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="handleout",cond=whenDispatch("out"))
				}	 
			}
		}
} 
