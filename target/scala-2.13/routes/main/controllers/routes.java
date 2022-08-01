// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseCustomerController CustomerController = new controllers.ReverseCustomerController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCustomerAccountController CustomerAccountController = new controllers.ReverseCustomerAccountController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCustomerDeviceController CustomerDeviceController = new controllers.ReverseCustomerDeviceController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseBankAccountController BankAccountController = new controllers.ReverseBankAccountController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseCustomerController CustomerController = new controllers.javascript.ReverseCustomerController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCustomerAccountController CustomerAccountController = new controllers.javascript.ReverseCustomerAccountController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCustomerDeviceController CustomerDeviceController = new controllers.javascript.ReverseCustomerDeviceController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseBankAccountController BankAccountController = new controllers.javascript.ReverseBankAccountController(RoutesPrefix.byNamePrefix());
  }

}
