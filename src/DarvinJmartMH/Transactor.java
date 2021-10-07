package DarvinJmartMH;


/**
 * Write a description of class Transaction here.
 *
 * @author Darvin
 * @version Modul 3
 */
interface Transactor
{
    public boolean validate();
    
    public Invoice perform();
}
