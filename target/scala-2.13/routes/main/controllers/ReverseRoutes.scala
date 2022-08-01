// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseCustomerController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def update(id:Int): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "customer/update/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:9
    def delete(id:Int): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "customer/delete/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:21
    def search(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "customer/search/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:7
    def view(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "customer/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:8
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "customer/create")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:12
  class ReverseCustomerAccountController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def view(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "customeraccount/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:13
    def create(customerId:Int, bankId:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "customeraccount/create" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("customerId", customerId)), Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("bankId", bankId)))))
    }
  
  }

  // @LINE:15
  class ReverseCustomerDeviceController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def view(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "device/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:16
    def create(customerId:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "device/create" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("customerId", customerId)))))
    }
  
  }

  // @LINE:18
  class ReverseBankAccountController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def view(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "bankaccount/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:19
    def create(id:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "bankaccount/create/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
  }


}
