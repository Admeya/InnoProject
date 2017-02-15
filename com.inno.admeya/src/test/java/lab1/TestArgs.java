package lab1;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Ирина on 09.02.2017.
 */
public class TestArgs {
    @Ignore
    @Test
    public void allArgumentsIsTrue() {
        String[] trueMas = {"src/main/resources/inp4.txt", "src/main/resources/inp5.txt", "src/main/resources/inp6.txt", "src/main/resources/inp1.txt", "src/main/resources/inp2.txt", "src/main/resources/inp6.txt"};
        File[] fileOfMas = Main.getResourcesNamesAndCheck(trueMas);

        Assert.assertNotNull(fileOfMas);
    }

    @Ignore
    @Test
    public void oneArgumentsIsFalse() {
        String[] trueMas = {"src/main/resources/inp4.txt", "/src/main/resources/inp5.txt", "src/main/resources/inp6.txt", "src/main/resources/inp1.txt", "src/main/resources/inp2.txt", "src/main/resources/inp6.txt"};
        File[] fileOfMas = Main.getResourcesNamesAndCheck(trueMas);

        Assert.assertNull(fileOfMas);
    }

    @Ignore
    @Test
    public void countSumOddAndPositive() {
        String[] str = {"2", "4", "16", "20", "1200"};
        for (int i = 0; i < str.length; i++) {
            Assert.assertEquals(Integer.parseInt(str[i]), OperationsOnResources.isSuitable(str[i]));
        }
    }

    @Ignore
    @Test
    public void countSumNonSuitable() {
        String[] str = {"1", "-5", "9", "11", "95"};
        for (int i = 0; i < str.length; i++) {
            Assert.assertNotEquals(Integer.parseInt(str[i]), OperationsOnResources.isSuitable(str[i]));
        }
    }

    @Test
    public void TrueSum() {
        Assert.assertEquals(OperationsOnResources.countingSum(12, 1000), 1012);
    }

    @Test
    public void isCorrectNumberFalseTest() {
        Assert.assertFalse(OperationsOnResources.isNumberCorrect("--12"));
        Assert.assertFalse(OperationsOnResources.isNumberCorrect("12--"));
        Assert.assertFalse(OperationsOnResources.isNumberCorrect("12/1"));
        Assert.assertFalse(OperationsOnResources.isNumberCorrect("1.2"));
        Assert.assertFalse(OperationsOnResources.isNumberCorrect("12."));
        Assert.assertFalse(OperationsOnResources.isNumberCorrect("12 000"));
    }

    @Test
    public void isCorrectNumberTrueTest() {
        Assert.assertTrue("IncorrectNumber 12", OperationsOnResources.isNumberCorrect("12"));
        Assert.assertTrue("IncorrectNumber -15", OperationsOnResources.isNumberCorrect("-15"));
        Assert.assertTrue("IncorrectNumber 10000", OperationsOnResources.isNumberCorrect("10000"));
        Assert.assertTrue("IncorrectNumber 000", OperationsOnResources.isNumberCorrect("000"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsFileNotFoundExceptionWithMessage() throws FileNotFoundException {
        String[] args = {"/src/main/resources/inp5.txt"};
        Main.getResourcesNamesAndCheck(args);
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage("Please, check input parameters, this file is not found " + new File(args[0]).getAbsolutePath());

    }

}
