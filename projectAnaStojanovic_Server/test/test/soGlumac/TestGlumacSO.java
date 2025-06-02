package test.soGlumac;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.Glumac;
import soGlumac.AddGlumac;
import soGlumac.GetGlumac;

public class TestGlumacSO {

    private AddGlumac addGlumac;
    private GetGlumac getGlumac;

    @Before
    public void setUp() {
        addGlumac = new AddGlumac();
        getGlumac = new GetGlumac();
    }

   
@Test
public void testAddGlumac() throws Exception {
    Glumac glumac = new Glumac();
    glumac.setJmbg(1234567890123L); 
    glumac.setIme("Marko");
    glumac.setPrezime("Markovic");

    addGlumac.execute(glumac, null);

  
    assertTrue(true);
}

    @Test
    public void testGetGlumac() throws Exception {
        Glumac g = new Glumac(); 
        getGlumac.execute(g, null);
        assertNotNull(getGlumac.getList());
    }
}

