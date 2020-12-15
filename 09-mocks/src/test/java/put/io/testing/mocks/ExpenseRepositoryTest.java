package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.util.Collections;


public class ExpenseRepositoryTest {
    @Test
    public void test_loadExpenses(){
        MyDB db = new MyDB();
        ExpenseRepository er = new ExpenseRepository(db);
        er.loadExpenses();
        assertEquals(er.getExpenses().size(), 0);
    }

    @Test
    public void test_expenseRepository2(){
        IFancyDatabase db = mock(MyDB.class);
        InOrder inOrder = inOrder(db);
        ExpenseRepository er = new ExpenseRepository(db);
        er.loadExpenses();
        when(db.queryAll()).thenReturn(Collections.emptyList());
        inOrder.verify(db).connect();
        inOrder.verify(db).queryAll();
        verify(db).close();
        assertEquals(er.getExpenses().size(), 0);
    }

    @Test
    public void test_expenseRepository3() {
        IFancyDatabase db = mock(MyDB.class);
        when(db.queryAll()).thenReturn(Collections.emptyList());
        InOrder inOrder = inOrder(db);
        ExpenseRepository er = new ExpenseRepository(db);
        er.loadExpenses();
        Expense expense = new Expense();
        for (int i = 0; i < 5; i++){
            er.addExpense(
                    expense
            );
        }
        er.saveExpenses();
        verify(db, times(5)).persist(any(Expense.class));
        inOrder.verify(db).connect();
        inOrder.verify(db).queryAll();
        inOrder.verify(db).close();
        er.loadExpenses();
        assertEquals(er.getExpenses().size(), 0);
    }
}
