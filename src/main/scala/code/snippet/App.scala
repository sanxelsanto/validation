/*
 * http://groups.google.com/group/liftweb/browse_thread/thread/b82f70da3f89453b/eee6c76243a5d5ae?lnk=gst&q=validate#eee6c76243a5d5ae
 */

package code
package snippet

import _root_.net.liftweb._
import http._
import SHtml._
import js._
import JsCmds._
import common._
import util._
import Helpers._
import model._
import scala.xml.NodeSeq
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js._
import net.liftweb.http.js.JE._
import net.liftweb.util.FieldError
import mapper._

class App extends StatefulSnippet with Logger {
  
  
  private var email = ""
  private var firstname = ""  
  private val from = S.referer openOr "/"
  

  def dispatch = {
    case _ => render
  }
  
  def render = {

     "name=email" #> SHtml.onEvents("onchange", "onblur", "keyup")( check_email _ )(SHtml.text(email, email = _)) &
     "name=firstname" #> SHtml.text(firstname, firstname = _) &
     "type=submit" #> SHtml.onSubmitUnit(process)
    
     }
     
     private def process() {    
    
        val user = User.create
    
        if (email.length == 0 ) {
          S.error("email not empty")
        }             
        else{
	  S.notice("registerUser OK")
        }
        
    }
    
    
  private def check_email(in:String): JsCmd = {
        
    val email = in.toLowerCase.trim

    User.find(By(User.email, email)) match {
      case Full(u : User) => S.error("S.error: email not available")
      case _ => S.notice("S.notice: email OK")
    }                   
    
    Noop
  }
    
     
}