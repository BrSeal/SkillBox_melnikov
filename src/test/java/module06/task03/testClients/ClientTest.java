package module06.task03.testClients;

import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;
import module06.task03.Clients.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;


public class ClientTest
{
    static BigDecimal expected;
    
    @Test
    @DisplayName ("Accounts existence")
    void testExistence()
    {
        for (int i = 0; i < 100; i++)
        {
            new FisClient();
        }
        for (int i = 0; i < 100; i++)
        {
            assertNotNull(Client.getClientByNumber(i));
        }
    }
    
    @Test
    @DisplayName ("get accounts by ID")
    void getAccountID()
    {
        for (int i = 0; i < 100; i++)
        {
            new FisClient();
        }
        for (int j = 0; j < 100; j++)
        {
            assertEquals(j, Client.getClientByNumber(j).getAccountID());
        }
    }
    
    @Test
    void depositEntityClient() throws Exception
    {
        EntityClient client = new EntityClient();
        
        expected = new BigDecimal("100.00");
        assertEquals(expected, client.deposit(100));
        Exception expectedEx1 = assertThrows(WrongMoneyAmountException.class, () -> client.deposit(0));
        assertEquals(WrongMoneyAmountException.class, expectedEx1.getClass());
        
        Exception expectedEx2 = assertThrows(WrongMoneyAmountException.class, () -> client.deposit(-1));
        assertEquals(WrongMoneyAmountException.class, expectedEx2.getClass());
        
        expected = new BigDecimal("1334.11");
        
        assertEquals(expected, client.deposit(1234.11123));
    }
    
    @Test
    void depositFisClient() throws Exception
    {
        FisClient client = new FisClient();
        
        expected = new BigDecimal(100).setScale(2, RoundingMode.FLOOR);
        assertEquals(expected, client.deposit(100));
        
        Exception expectedEx1 = assertThrows(WrongMoneyAmountException.class, () -> client.deposit(0));
        assertEquals(WrongMoneyAmountException.class, expectedEx1.getClass());
        
        Exception expectedEx2 = assertThrows(WrongMoneyAmountException.class, () -> client.deposit(-1));
        assertEquals(WrongMoneyAmountException.class, expectedEx2.getClass());
        
        expected = new BigDecimal("1334.11");
        assertEquals(expected, client.deposit(1234.11123));
    }
    
    @Test
    void depositIndividualClient() throws Exception
    {
        IndividualClient client = new IndividualClient();
        
        expected = new BigDecimal("99.00");
        assertEquals(expected, client.deposit(100));
        
        Exception expectedEx1 = assertThrows(WrongMoneyAmountException.class, () -> client.deposit(0));
        assertEquals(WrongMoneyAmountException.class, expectedEx1.getClass());
        
        Exception expectedEx2 = assertThrows(WrongMoneyAmountException.class, () -> client.deposit(-1));
        assertEquals(WrongMoneyAmountException.class, expectedEx2.getClass());
        
        expected = new BigDecimal("1326.94067385").setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expected, client.deposit(1234.11123));
    }
    
    @Test
    void withdrawEntityClientExceptionsThrown()
    {
        EntityClient client = new EntityClient();
        
        Exception expectedEx1 = assertThrows(WrongMoneyAmountException.class, () -> client.withdraw(0));
        assertEquals(WrongMoneyAmountException.class, expectedEx1.getClass());
        
        Exception expectedEx2 = assertThrows(WrongMoneyAmountException.class, () -> client.withdraw(-1));
        assertEquals(WrongMoneyAmountException.class, expectedEx2.getClass());
        
        Exception expectedEx3 = assertThrows(NotEnoughMoneyException.class, () -> client.withdraw(50));
        assertEquals(NotEnoughMoneyException.class, expectedEx3.getClass());
    }
    
    @Test
    void withdrawEntityClient() throws Exception
    {
        EntityClient client = new EntityClient();
        client.deposit(100);
        
        expected = BigDecimal.valueOf(50 * (1 - client.getWithdrawalFee())).setScale(2, RoundingMode.FLOOR);
        assertEquals(expected, client.withdraw(50));
    }
    
    
    @Test
    void withdrawFisClientExceptionsThrown()
    {
        FisClient client = new FisClient();
        
        Exception expectedEx1 = assertThrows(WrongMoneyAmountException.class, () -> client.withdraw(0));
        assertEquals(WrongMoneyAmountException.class, expectedEx1.getClass());
        
        Exception expectedEx2 = assertThrows(WrongMoneyAmountException.class, () -> client.withdraw(-1));
        assertEquals(WrongMoneyAmountException.class, expectedEx2.getClass());
        
        Exception expectedEx3 = assertThrows(NotEnoughMoneyException.class, () -> client.withdraw(50));
        assertEquals(NotEnoughMoneyException.class, expectedEx3.getClass());
    }
    
    @Test
    void withdrawFisClient() throws Exception
    {
        FisClient client = new FisClient();
        client.deposit(100);
        
        expected = new BigDecimal("50.00");
        assertEquals(expected, client.withdraw(50));
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR), client.withdraw(50));
    }
    
    @Test
    void withdrawIndividualClientExceptionsThrown()
    {
        FisClient client = new FisClient();
        
        Exception expectedEx1 = assertThrows(WrongMoneyAmountException.class, () -> client.withdraw(0));
        assertEquals(WrongMoneyAmountException.class, expectedEx1.getClass());
        
        Exception expectedEx2 = assertThrows(WrongMoneyAmountException.class, () -> client.withdraw(-1));
        assertEquals(WrongMoneyAmountException.class, expectedEx2.getClass());
        
        Exception expectedEx3 = assertThrows(NotEnoughMoneyException.class, () -> client.withdraw(50));
        assertEquals(NotEnoughMoneyException.class, expectedEx3.getClass());
    }
    
    @Test
    void withdrawIndividualClient() throws Exception
    {
        FisClient client = new FisClient();
        client.deposit(100);
        
        expected = new BigDecimal("50.00");
        assertEquals(expected, client.withdraw(50));
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR), client.withdraw(50));
    }
    
    @Test
    void getClientByID()
    {
        for (int i = 0; i < 100; i++)
        {
            new FisClient();
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i, Client.getClientByNumber(i).getAccountID());
        }
        
    }
    
    @Test
    void getBalance() throws Exception
    {
        Client client = new FisClient();
        //BigDecimal expected;
        
        expected = BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR);
        
        assertEquals(expected, client.getBalance());
        
        client.deposit(100);
        expected = new BigDecimal("100.00");
        assertEquals(expected, client.getBalance());
        
        client.withdraw(50);
        expected = new BigDecimal("50.00");
        assertEquals(expected, client.getBalance());
        
    }
}