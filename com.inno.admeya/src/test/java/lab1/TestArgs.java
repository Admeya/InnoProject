package lab1;

import org.junit.jupiter.api.Test;

/**
 * Created by Ирина on 09.02.2017.
 */
public class TestArgs {
    @Test
    public void allArgumentsIsTrue() {
        String[] trueMas = {"src/main/resources/inp4.txt", "src/main/resources/inp5.txt", "src/main/resources/inp6.txt", "src/main/resources/inp1.txt", "src/main/resources/inp2.txt", "src/main/resources/inp6.txt"};

        Main.getResourcesNamesAndCheck(trueMas);
    }

}
