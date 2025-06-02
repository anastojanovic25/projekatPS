package test.soKorisnik;

import model.AbstractDomainObject;
import model.Korisnik;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import soKorisnik.AddKorisnik;

public class LoginKorisnikTest {

    private AddKorisnik addKorisnik;
    private Korisnik testKorisnik;

    @Before
    public void setUp() {
        addKorisnik = new AddKorisnik();
        testKorisnik = new Korisnik();
        testKorisnik.setEmail("test@example.com");
        testKorisnik.setPassword("test123");
        testKorisnik.setIme("Test");
        testKorisnik.setPrezime("User");
        testKorisnik.setBroj("123456789");
        testKorisnik.setPol("M");
    }

    @Test
    public void testAddKorisnikSuccess() {
        try {
            addKorisnik.execute(testKorisnik, null);
            int generatedId = addKorisnik.getId();
            assertTrue("ID should be greater than 0", generatedId > 0);
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
      @Test(expected = Exception.class)
    public void testAddKorisnikInvalidObject() throws Exception {
        addKorisnik.execute((AbstractDomainObject) new Object(), null);
    }

}
