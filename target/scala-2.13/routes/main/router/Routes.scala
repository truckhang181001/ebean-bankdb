// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  CustomerController_0: controllers.CustomerController,
  // @LINE:12
  CustomerAccountController_1: controllers.CustomerAccountController,
  // @LINE:15
  CustomerDeviceController_2: controllers.CustomerDeviceController,
  // @LINE:18
  BankAccountController_3: controllers.BankAccountController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    CustomerController_0: controllers.CustomerController,
    // @LINE:12
    CustomerAccountController_1: controllers.CustomerAccountController,
    // @LINE:15
    CustomerDeviceController_2: controllers.CustomerDeviceController,
    // @LINE:18
    BankAccountController_3: controllers.BankAccountController
  ) = this(errorHandler, CustomerController_0, CustomerAccountController_1, CustomerDeviceController_2, BankAccountController_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, CustomerController_0, CustomerAccountController_1, CustomerDeviceController_2, BankAccountController_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.CustomerController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customer/""" + "$" + """id<[^/]+>""", """controllers.CustomerController.view(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customer/create""", """controllers.CustomerController.create(request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customer/delete/""" + "$" + """id<[^/]+>""", """controllers.CustomerController.delete(id:Int)"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customer/update/""" + "$" + """id<[^/]+>""", """controllers.CustomerController.update(id:Int, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customeraccount/""" + "$" + """id<[^/]+>""", """controllers.CustomerAccountController.view(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customeraccount/create""", """controllers.CustomerAccountController.create(customerId:Int, bankId:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """device/""" + "$" + """id<[^/]+>""", """controllers.CustomerDeviceController.view(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """device/create""", """controllers.CustomerDeviceController.create(customerId:Int, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bankaccount/""" + "$" + """id<[^/]+>""", """controllers.BankAccountController.view(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bankaccount/create/""" + "$" + """id<[^/]+>""", """controllers.BankAccountController.create(id:Int, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customer/search/""" + "$" + """id<[^/]+>""", """controllers.CustomerController.search(id:Int)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_CustomerController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_CustomerController_index0_invoker = createInvoker(
    CustomerController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ Default path will just redirect to the computer list""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_CustomerController_view1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customer/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerController_view1_invoker = createInvoker(
    CustomerController_0.view(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "view",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """customer/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_CustomerController_create2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customer/create")))
  )
  private[this] lazy val controllers_CustomerController_create2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CustomerController_0.create(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "create",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """customer/create""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_CustomerController_delete3_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customer/delete/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerController_delete3_invoker = createInvoker(
    CustomerController_0.delete(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "delete",
      Seq(classOf[Int]),
      "DELETE",
      this.prefix + """customer/delete/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_CustomerController_update4_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customer/update/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerController_update4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CustomerController_0.update(fakeValue[Int], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "update",
      Seq(classOf[Int], classOf[play.mvc.Http.Request]),
      "PATCH",
      this.prefix + """customer/update/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_CustomerAccountController_view5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customeraccount/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerAccountController_view5_invoker = createInvoker(
    CustomerAccountController_1.view(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerAccountController",
      "view",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """customeraccount/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_CustomerAccountController_create6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customeraccount/create")))
  )
  private[this] lazy val controllers_CustomerAccountController_create6_invoker = createInvoker(
    CustomerAccountController_1.create(fakeValue[Int], fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerAccountController",
      "create",
      Seq(classOf[Int], classOf[Int]),
      "POST",
      this.prefix + """customeraccount/create""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_CustomerDeviceController_view7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("device/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerDeviceController_view7_invoker = createInvoker(
    CustomerDeviceController_2.view(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerDeviceController",
      "view",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """device/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_CustomerDeviceController_create8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("device/create")))
  )
  private[this] lazy val controllers_CustomerDeviceController_create8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CustomerDeviceController_2.create(fakeValue[Int], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerDeviceController",
      "create",
      Seq(classOf[Int], classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """device/create""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_BankAccountController_view9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bankaccount/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BankAccountController_view9_invoker = createInvoker(
    BankAccountController_3.view(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BankAccountController",
      "view",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """bankaccount/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_BankAccountController_create10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bankaccount/create/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BankAccountController_create10_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      BankAccountController_3.create(fakeValue[Int], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BankAccountController",
      "create",
      Seq(classOf[Int], classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bankaccount/create/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_CustomerController_search11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customer/search/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerController_search11_invoker = createInvoker(
    CustomerController_0.search(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "search",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """customer/search/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_CustomerController_index0_route(params@_) =>
      call { 
        controllers_CustomerController_index0_invoker.call(CustomerController_0.index())
      }
  
    // @LINE:7
    case controllers_CustomerController_view1_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_CustomerController_view1_invoker.call(CustomerController_0.view(id))
      }
  
    // @LINE:8
    case controllers_CustomerController_create2_route(params@_) =>
      call { 
        controllers_CustomerController_create2_invoker.call(
          req => CustomerController_0.create(req))
      }
  
    // @LINE:9
    case controllers_CustomerController_delete3_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_CustomerController_delete3_invoker.call(CustomerController_0.delete(id))
      }
  
    // @LINE:10
    case controllers_CustomerController_update4_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_CustomerController_update4_invoker.call(
          req => CustomerController_0.update(id, req))
      }
  
    // @LINE:12
    case controllers_CustomerAccountController_view5_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_CustomerAccountController_view5_invoker.call(CustomerAccountController_1.view(id))
      }
  
    // @LINE:13
    case controllers_CustomerAccountController_create6_route(params@_) =>
      call(params.fromQuery[Int]("customerId", None), params.fromQuery[Int]("bankId", None)) { (customerId, bankId) =>
        controllers_CustomerAccountController_create6_invoker.call(CustomerAccountController_1.create(customerId, bankId))
      }
  
    // @LINE:15
    case controllers_CustomerDeviceController_view7_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_CustomerDeviceController_view7_invoker.call(CustomerDeviceController_2.view(id))
      }
  
    // @LINE:16
    case controllers_CustomerDeviceController_create8_route(params@_) =>
      call(params.fromQuery[Int]("customerId", None)) { (customerId) =>
        controllers_CustomerDeviceController_create8_invoker.call(
          req => CustomerDeviceController_2.create(customerId, req))
      }
  
    // @LINE:18
    case controllers_BankAccountController_view9_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_BankAccountController_view9_invoker.call(BankAccountController_3.view(id))
      }
  
    // @LINE:19
    case controllers_BankAccountController_create10_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_BankAccountController_create10_invoker.call(
          req => BankAccountController_3.create(id, req))
      }
  
    // @LINE:21
    case controllers_CustomerController_search11_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_CustomerController_search11_invoker.call(CustomerController_0.search(id))
      }
  }
}
