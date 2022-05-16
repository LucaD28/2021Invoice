public abstract class Business {


    private static final int VAT = 21;

    public Business () {

    }

    private double getPriceIncludingVAT(double priceExcludingVAT) {
        return (1 + VAT / 100.0) * priceExcludingVAT;
    }

    private double getVAT (double priceExcludingVAT) {
        return VAT / 100.0 * priceExcludingVAT;
    }

    public String getLastLinesOfInvoice (double priceExcludingVAT) {

        StringBuilder lines = new StringBuilder ();
        lines.append (InvoiceFormatter.getLine (true, "----------+"));
        lines.append (InvoiceFormatter.getLine (true, "Totaal    € %8.2f ", priceExcludingVAT));
        if (this instanceof NormaleBusiness) {
            double vat = getVAT (priceExcludingVAT);
            double priceIncludingVAT = getPriceIncludingVAT (priceExcludingVAT);

            lines.append (InvoiceFormatter.getLine (true, "21%% BTW   € %8.2f ", vat));
            lines.append (InvoiceFormatter.getLine (true, "----------+"));
            lines.append (InvoiceFormatter.getLine (true, "Totaal    € %8.2f ", priceIncludingVAT));
            return lines.toString ();
        }
        else { // type = NORMAL
            lines.append (InvoiceFormatter.getEmptyLine ());
            lines.append (InvoiceFormatter.getLine (true, "Factuur is vrijgesteld van OB o.g.v. artikel 25 Wet OB "));
            return lines.toString();
        }
    }

}