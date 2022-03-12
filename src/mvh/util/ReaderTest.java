package mvh.util;

import mvh.world.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @Test
    void loadWorld() {
        World test=new World(3,3);
        assertEquals(1,1);
    }
}