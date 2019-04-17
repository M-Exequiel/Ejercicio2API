public enum CriterioOrdenAgencia {

    ADDRESS("Address"),
    CODE("Code"),
    DISTANCE("Distance");

    private String criterio;

    CriterioOrdenAgencia(String criterio) {
        this.criterio=criterio;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
}
