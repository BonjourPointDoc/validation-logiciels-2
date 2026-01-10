# Exercices
## Exercise 6 (Repo 2): Turn BankApplication into a Maven project

```bash
    mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1
```

![exo6](img/capture1.png)

## Exercise 7 (Repo 2): Add test dependencies and explore the Maven lifecycle
I updated the **junit** dependency to a more recent version and added **hamcrest**.
```xml
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.hamcrest</groupId>
      <artifactId>hamcrest-library</artifactId>
      <version>1.3</version>
      <scope>test</scope>
    </dependency>
```
### mvn clean
**Phases :**  
- Deleting **/target** folder.
![exo6](img/capture2.png)
**Files/folders in target :**
- None

### mvn test
**Phases :**
![exo6](img/capture3.png)
- Validating resources
- Compiling
- Tests
**Files/folders in target :**
![exo6](img/capture4.png)

### mvn package
**Phases :**
![exo6](img/capture5.png)
- Validating resources
- Compiling
- Tests
- Packaging phase -> creates a .jar file of the project
**Files/folders in target :**
- Creates a jar file :
![exo6](img/capture6.png)

### mvn verify
**Difference from test :** 
mvn verify goes further than test in the phases by building the project as well.
**Difference from package :**
In theory, mvn verify runs integration tests as well compared to package. However, I wasn't able to see a difference on this project

## Exercise 8 (Repo 2): Write unit tests for the bank domain
I choose to test the deposit and withdraw methods :

### deposit
```java
    @Test
    public void test_deposit() { // Happy path test
        int deposit = 100;
        bank.addAccount(bankAccount, accountId);
    
        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.depositMoney(deposit);
        assertTrue(res);
        assertEquals(1100, bankAccount.getBalance(), 0f);
    }
    
    @Test
    public void test_deposit_fail() {  // Edge case test
        int deposit = -100;
        bank.addAccount(bankAccount, accountId);
    
        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.depositMoney(deposit);
        assertFalse(res);
        assertEquals(1000, bankAccount.getBalance(), 0f);
    }
```

### withdrawMoney()
```java
    @Test
    public void test_withdrawMoney() throws Exception { // Happy path test
        int withdraw = 100;
        bank.addAccount(bankAccount, accountId);
    
        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.withdrawMoney(withdraw);
        assertTrue(res);
        assertEquals(100, bankAccount.getAmountWithdrawn(), 0f);
        assertEquals(900, bankAccount.getBalance(), 0f);
    
    }
    
    @Test
    public void test_withdrawMoney_fail() throws Exception {  // Edge case test
        int withdraw = 6000;
        bank.addAccount(bankAccount, accountId);
    
        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.withdrawMoney(withdraw);
        assertFalse(res);
        assertEquals(0, bankAccount.getAmountWithdrawn(), 0f);
        assertEquals(1000, bankAccount.getBalance(), 0f);
    
    }   
```
I ran my tests, they failed as depositMoney does not return a boolean value.
![exo6](img/capture7.png)

My tests were correct so I decided to change that and adapted depositMoney accordingly :
```java
    public boolean depositMoney(double depositAmount) {
        if (depositAmount >= 0) {
            balance = balance + depositAmount;
            return true;
        }
        return false;
    }
```
![exo6](img/capture8.png)

    