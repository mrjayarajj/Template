package org.thoughtworks.sales.stage4;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

abstract class AbstractItemModel{}
class ItemModel extends AbstractItemModel{}

/**
 * @navassoc 1 - 1..* AbstractOrderEntryModel
 * @navassoc - - - PaymentInfoModel
 * @opt nodefillcolor darkseagreen3
 */
class AbstractOrderModel /*extends ItemModel*/{
}
/**
 * @opt nodefillcolor darkseagreen4
 */
class AbstractOrderEntryModel /*extends ItemModel*/{}

/** 
 * @opt nodefillcolor darkseagreen3
 */
class CartModel extends AbstractOrderModel{}
/**
 * @opt nodefillcolor darkseagreen4
 */
class CartEntryModel extends AbstractOrderEntryModel{}

/**
 * @opt nodefillcolor darkseagreen3
 */
class OrderModel extends AbstractOrderModel{}
/**
 * @opt nodefillcolor darkseagreen4
 */
class OrderEntryModel extends AbstractOrderEntryModel{}

/**
 * @hidden
 */
abstract class AbstractService {}
/**
 * @hidden
 */
abstract class AbstractBusinessService extends AbstractService{}

/**
 * @opt nodefillcolor green
 * @navassoc - - - OrderDao
 */
interface AbstractOrderService<O extends AbstractOrderModel, E extends AbstractOrderEntryModel>{}

/**
 * @hidden
 */
abstract class DefaultAbstractOrderService<O extends AbstractOrderModel, E extends AbstractOrderEntryModel> extends AbstractBusinessService implements AbstractOrderService<O, E>{}

/**
 * @opt nodefillcolor green
 */
interface AbstractOrderEntryService<E extends AbstractOrderEntryModel>{}
/** 
 * @navassoc - - - OrderEntryModel
 * @opt nodefillcolor green
 */
interface OrderEntryService extends AbstractOrderEntryService<OrderEntryModel>{}

/** 
 * @navassoc - - - CartModel 
 * @navassoc - - - CartEntryModel
 * @opt nodefillcolor green
 */
interface CartService extends AbstractOrderService<CartModel, CartEntryModel>{
	void addToCart(CartModel cart, ProductModel product, long quantity, UnitModel unit) throws InvalidCartException;
	void setSessionCart(CartModel cart);
	CartModel getSessionCart();
	void removeSessionCart();
	boolean hasSessionCart();
	boolean hasCart();
	boolean calculateCart(CartModel cartModel);
	void updateQuantities(CartModel cart, List<Long> quantities);
	void updateQuantities(CartModel cart, Map<Integer, Long> quantities);
	void appendToCart(final CartModel sourceCart, final CartModel targetCart);
	void changeCurrentCartUser(final UserModel user);
	void changeSessionCartCurrency(CurrencyModel currency);
}

/**
 * @hidden
 */
class DefaultCartService extends DefaultAbstractOrderService<CartModel, CartEntryModel> implements CartService{
	public void addToCart(CartModel cart, ProductModel product, long quantity, UnitModel unit) throws InvalidCartException{}
	public void setSessionCart(CartModel cart){}
	public CartModel getSessionCart(){return null;}
	public void removeSessionCart(){}
	public boolean hasSessionCart(){return false;}
	public boolean hasCart(){return false;}
	public boolean calculateCart(CartModel cartModel){return false;}
	public void updateQuantities(CartModel cart, List<Long> quantities){}
	public void updateQuantities(CartModel cart, Map<Integer, Long> quantities){}
	public void appendToCart(final CartModel sourceCart, final CartModel targetCart){}
	public void changeCurrentCartUser(final UserModel user){}
	public void changeSessionCartCurrency(CurrencyModel currency){}
}

/**
 * @navassoc  - - - OrderModel 
 * @navassoc  - - - OrderEntryModel
 * @opt attributes
 * @opt visibility
 * @opt types
 * @opt nodefillcolor green
 */
interface OrderService extends AbstractOrderService<OrderModel, OrderEntryModel>{
	OrderModel createOrderFromCart(CartModel cart) throws InvalidCartException;
	void submitOrder(OrderModel order);
	OrderModel placeOrder(final CartModel cart, AddressModel deliveryAddress, AddressModel paymentAddress,PaymentInfoModel paymentInfo) throws InvalidCartException;
	boolean calculateOrder(AbstractOrderModel order);
	OrderEntryModel addNewEntry(final OrderModel order, final ProductModel product, final long qty, final UnitModel unit,final int number, final boolean addToPresent);
	AbstractOrderEntryModel addNewEntry(final ComposedTypeModel entryType, final OrderModel order, final ProductModel product,final long qty, final UnitModel unit, final int number, final boolean addToPresent);
}

/**
 * @hidden
 */
class DefaultOrderService extends DefaultAbstractOrderService<OrderModel, OrderEntryModel> implements OrderService{
	public OrderModel createOrderFromCart(CartModel cart) throws InvalidCartException{return null;}
	public void submitOrder(OrderModel order){}
	public OrderModel placeOrder(final CartModel cart, AddressModel deliveryAddress, AddressModel paymentAddress,PaymentInfoModel paymentInfo) throws InvalidCartException{return null;}
	public boolean calculateOrder(AbstractOrderModel order){return false;}
	public OrderEntryModel addNewEntry(final OrderModel order, final ProductModel product, final long qty, final UnitModel unit,final int number, final boolean addToPresent){return null;}
	public AbstractOrderEntryModel addNewEntry(final ComposedTypeModel entryType, final OrderModel order, final ProductModel product,final long qty, final UnitModel unit, final int number, final boolean addToPresent){return null;}
}

class PaymentInfoModel /*extends ItemModel*/{}
class CreditCardPaymentInfoModel extends PaymentInfoModel{}
class AdvancePaymentInfoModel extends PaymentInfoModel{}
class DebitPaymentInfoModel extends PaymentInfoModel{}
class InvoicePaymentInfoModel extends PaymentInfoModel{}

class PaymentTransactionEntryModel extends ItemModel{}

class BusinessException extends Exception{}
class InvalidCartException extends BusinessException{}

class C2LItemModel extends ItemModel{}
class CurrencyModel extends C2LItemModel{}

class PrincipalModel extends ItemModel{}
class UserModel extends PrincipalModel{}

class AddressModel extends ItemModel{}
class ProductModel extends ItemModel{}
class UnitModel extends ItemModel{}
class DeliveryModeModel extends ItemModel{}
class TypeManagerManagedModel extends ItemModel{}
class TypeModel extends TypeManagerManagedModel{}
class ComposedTypeModel extends TypeModel{}

interface OrderDao{}
///////////////////////////////////////////////////////////////////////

/**
 *  @depend - - - AbstractOrderModel 
 */
interface CalculationService{}
interface ProductService{}
/**
 * @navassoc 1 - 1 CommandFactoryRegistry
 */
abstract interface PaymentService{}

/**
 * @navassoc 1 - 1..* CommandFactory
 */
interface CommandFactoryRegistry{}

/**
 * @navassoc 1 - 1..* Command
 * @navassoc - - - Provider
 */
interface CommandFactory{}

interface Command{}



interface CommerceCartService{}

/**
 * @navassoc - - - CartService 
 * @navassoc - - - ProductService
 * @navassoc - - - CommerceCartService
 */
interface CartFacade{}

/**
 * @navassoc - - - OrderService 
 * @navassoc - - - CalculationService
 * @navassoc - - - PaymentService
 */
interface CommerceCheckoutService{
	boolean setDeliveryAddress(CartModel cartModel, AddressModel addressModel);
	boolean setDeliveryAddress(CartModel cartModel, AddressModel addressModel, boolean flagAsDeliveryAddress);
	boolean setDeliveryMode(CartModel cartModel, DeliveryModeModel deliveryModeModel);
	void validateDeliveryMode(CartModel cartModel);
	boolean setPaymentInfo(CartModel cartModel, PaymentInfoModel paymentInfoModel);
	PaymentTransactionEntryModel authorizePayment(CartModel cartModel, String securityCode, String paymentProvider);
	PaymentTransactionEntryModel authorizePayment(CartModel cartModel, String securityCode, String paymentProvider,BigDecimal amount);
	OrderModel placeOrder(CartModel cartModel) throws InvalidCartException;
	String getPaymentProvider();
	boolean removeDeliveryMode(CartModel cartModel);

}

/**
 * @navassoc - - - CartFacade 
 * @navassoc - - - CartService
 * @navassoc - - - CommerceCheckoutService
 */
interface CheckoutFacade{}
