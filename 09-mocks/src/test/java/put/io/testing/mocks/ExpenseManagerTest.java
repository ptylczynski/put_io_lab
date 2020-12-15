package put.io.testing.mocks;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExpenseManagerTest {
    @Test
    public void test_expenseManager(){
        ExpenseRepository er = mock(ExpenseRepository.class);
        when(er.getExpenses()).thenReturn(
                List.of(
                    new Expense(
                            "Test1", "Cat1", 20
                    ), new Expense(
                            "Test2", "Cat1", 31
                        ), new Expense(
                                "Test3", "Cat2", 124
                        )
                )
        );
        ExpenseManager em = new ExpenseManager(er, new FancyService());
        assertEquals(em.calculateTotal(), 20 + 31 + 124);
    }

    @Test
    public void test_expenseManager2(){
        ExpenseRepository er = mock(ExpenseRepository.class);
        when(er.getExpensesByCategory(eq("Car"))).thenReturn(List.of(
                new Expense("Title1", "Car", 21),
                new Expense("Title2", "Car", 124),
                new Expense("Title3", "Car", 612)
        ));
        when(er.getExpensesByCategory(eq("Home"))).thenReturn(List.of(
                new Expense("Title1", "Home", 21),
                new Expense("Title2", "Home", 31),
                new Expense("Title3", "Home", 51)
        ));
        ExpenseManager em = new ExpenseManager(er, new FancyService());
        assertEquals(er.getExpensesByCategory("Car").size(), 3);
        assertEquals(er.getExpensesByCategory("Home").size(), 3);
        assertEquals(em.calculateTotalForCategory("Car"), 21 + 124 + 612);
        assertEquals(em.calculateTotalForCategory("Home"), 21 + 31 + 51);
        assertEquals(em.calculateTotalForCategory("Test1"), 0);
    }

    @Test
    public void test_expenseManager3() throws ConnectException {
        FancyService fs = mock(FancyService.class);
        when(fs.convert(anyLong(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer<Object>() {
                    @Override
                    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Object[] args = invocationOnMock.getArguments();
                        return 4.0 * (long)args[0];
                    }
                }
        );
        ExpenseRepository er = mock(ExpenseRepository.class);
        ExpenseManager em = new ExpenseManager(er, fs);
        assertEquals(em.convertExpenseToDollars(new Expense("Title1", "car1", 31L)), 31 * 4);
    }
}
