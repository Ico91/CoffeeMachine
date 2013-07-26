package coffee_machine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import coffee_machine.administration.AdministrationFlowTests;
import coffee_machine.finalize_order.OrderFinalizeFlowTests;
import coffee_machine.insufficient_amount.InsufficientAmountFlowTests;
import coffee_machine.list_drinks.DrinkListFlowTests;
import coffee_machine.model.DrinksContainerTests;
import coffee_machine.model.MoneyAmountTests;
import coffee_machine.order.OrderFlowTests;
import coffee_machine.payment.PaymentFlowTests;
import coffee_machine.reports.DrinksReportFlowTests;
import coffee_machine.reports.transformers.OrderedDrinksContainerToDTOTests;
import coffee_machine.storage.transformers.DTOToCoffeeMachineStateTests;
import coffee_machine.storage.transformers.DTOToDrinksContainerTransformerTests;
import coffee_machine.storage.transformers.DTOToMoneyAmountTransformerTests;
import coffee_machine.xml_io.XMLIOTests;

@RunWith(Suite.class)
@SuiteClasses({ AdministrationFlowTests.class, OrderFinalizeFlowTests.class,
		InsufficientAmountFlowTests.class, DrinkListFlowTests.class,
		DrinksContainerTests.class, MoneyAmountTests.class,
		OrderFlowTests.class, PaymentFlowTests.class,
		DrinksReportFlowTests.class, OrderedDrinksContainerToDTOTests.class,
		DTOToCoffeeMachineStateTests.class,
		DTOToDrinksContainerTransformerTests.class,
		DTOToMoneyAmountTransformerTests.class, XMLIOTests.class })

public class AllTests {

}
