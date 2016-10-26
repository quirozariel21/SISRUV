/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author KRETCO
 */
public class View_violenceReport {
    
    private String descripcion;
    private String sexo;
    private BigDecimal contador;
    private BigDecimal hombre;
    private BigDecimal mujer;
    private BigDecimal tota;
    //
    private BigDecimal percent;
    private boolean columnBool;
    //

    public boolean isColumnBool() {
        return columnBool;
    }

    public void setColumnBool(boolean columnBool) {
        this.columnBool = columnBool;
    }
    
    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
    
    
    /****** Ocupacion principal ****/
    
    private int estudiante;
    private int cuentapropia;   
    private int labores;
    private int empleada;
    private int otraocupacion;
    
      public int getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(int estudiante) {
        this.estudiante = estudiante;
    }

    public int getLabores() {
        return labores;
    }

    public void setLabores(int labores) {
        this.labores = labores;
    }

    public int getEmpleada() {
        return empleada;
    }

    public void setEmpleada(int empleada) {
        this.empleada = empleada;
    }

    public int getCuentapropia() {
        return cuentapropia;
    }

    public void setCuentapropia(int cuentapropia) {
        this.cuentapropia = cuentapropia;
    }

    public int getOtraocupacion() {
        return otraocupacion;
    }

    public void setOtraocupacion(int otraocupacion) {
        this.otraocupacion = otraocupacion;
    }
    
    /****** Ocupacion principal ****/
   

    /******EStado Civil****/
    
     public int getSoltero() {
        return soltero;
    }

    public void setSoltero(int soltero) {
        this.soltero = soltero;
    }

    public int getCasado() {
        return casado;
    }

    public void setCasado(int casado) {
        this.casado = casado;
    }

    public int getConcubino() {
        return concubino;
    }

    public void setConcubino(int concubino) {
        this.concubino = concubino;
    }

    public int getSeparado() {
        return separado;
    }

    public void setSeparado(int separado) {
        this.separado = separado;
    }

    public int getDivorciado() {
        return divorciado;
    }

    public void setDivorciado(int divorciado) {
        this.divorciado = divorciado;
    }

    public int getViudo() {
        return viudo;
    }
    
    public void setViudo(int viudo) {
        this.viudo = viudo;
    }

    private int soltero;
    private int casado;
    private int concubino;
    private int separado;
    private int divorciado;
    private int viudo;
    
    /******EStado Civil****/
    /****Propiedad***/
   

    private int propia;
    private int anticretico;
    private int alquiler;
    private int cedida;
    private int prestada;
    private int otra;
    
    public int getPropia() {
        return propia;
    }

    public void setPropia(int propia) {
        this.propia = propia;
    }

    public int getAnticretico() {
        return anticretico;
    }

    public void setAnticretico(int anticretico) {
        this.anticretico = anticretico;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public int getCedida() {
        return cedida;
    }

    public void setCedida(int cedida) {
        this.cedida = cedida;
    }

    public int getPrestada() {
        return prestada;
    }

    public void setPrestada(int prestada) {
        this.prestada = prestada;
    }

    public int getOtra() {
        return otra;
    }
    
    public void setOtra(int otra) {
        this.otra = otra;
    }
    /****Propiedad***/
    private String columna1;
    private String columna2;
    private String columna3;
    private String columna4;
    private String columna5;
    private String columna6;
    private String columna7;
    private String columna8;
    private String columna9;
    private String columna10;
    private String columna11;
    private String columna12;
    private String columna13;
    private String columna14;
    private String columna15;
    private String columna16;
    private String columna17;
    private String columna18;
    private String columna19;
    private String columna20;

    public View_violenceReport() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigDecimal getContador() {
        return contador;
    }

    public void setContador(BigDecimal contador) {
        this.contador = contador;
    }

    public BigDecimal getHombre() {
        return hombre;
    }

    public void setHombre(BigDecimal hombre) {
        this.hombre = hombre;
    }

    public BigDecimal getMujer() {
        return mujer;
    }

    public void setMujer(BigDecimal mujer) {
        this.mujer = mujer;
    }

    public BigDecimal getTota() {
        return tota;
    }

    public void setTota(BigDecimal tota) {
        this.tota = tota;
    }

    
    public String getColumna1() {
        return columna1;
    }

    public void setColumna1(String columna1) {
        this.columna1 = columna1;
    }

    public String getColumna2() {
        return columna2;
    }

    public void setColumna2(String columna2) {
        this.columna2 = columna2;
    }

    public String getColumna3() {
        return columna3;
    }

    public void setColumna3(String columna3) {
        this.columna3 = columna3;
    }

    public String getColumna4() {
        return columna4;
    }

    public void setColumna4(String columna4) {
        this.columna4 = columna4;
    }

    public String getColumna5() {
        return columna5;
    }

    public void setColumna5(String columna5) {
        this.columna5 = columna5;
    }

    public String getColumna6() {
        return columna6;
    }

    public void setColumna6(String columna6) {
        this.columna6 = columna6;
    }

    public String getColumna7() {
        return columna7;
    }

    public void setColumna7(String columna7) {
        this.columna7 = columna7;
    }

    public String getColumna8() {
        return columna8;
    }

    public void setColumna8(String columna8) {
        this.columna8 = columna8;
    }

    public String getColumna9() {
        return columna9;
    }

    public void setColumna9(String columna9) {
        this.columna9 = columna9;
    }

    public String getColumna10() {
        return columna10;
    }

    public void setColumna10(String columna10) {
        this.columna10 = columna10;
    }

    public String getColumna11() {
        return columna11;
    }

    public void setColumna11(String columna11) {
        this.columna11 = columna11;
    }

    public String getColumna12() {
        return columna12;
    }

    public void setColumna12(String columna12) {
        this.columna12 = columna12;
    }

    public String getColumna13() {
        return columna13;
    }

    public void setColumna13(String columna13) {
        this.columna13 = columna13;
    }

    public String getColumna14() {
        return columna14;
    }

    public void setColumna14(String columna14) {
        this.columna14 = columna14;
    }

    public String getColumna15() {
        return columna15;
    }

    public void setColumna15(String columna15) {
        this.columna15 = columna15;
    }

    public String getColumna16() {
        return columna16;
    }

    public void setColumna16(String columna16) {
        this.columna16 = columna16;
    }

    public String getColumna17() {
        return columna17;
    }

    public void setColumna17(String columna17) {
        this.columna17 = columna17;
    }

    public String getColumna18() {
        return columna18;
    }

    public void setColumna18(String columna18) {
        this.columna18 = columna18;
    }

    public String getColumna19() {
        return columna19;
    }

    public void setColumna19(String columna19) {
        this.columna19 = columna19;
    }

    public String getColumna20() {
        return columna20;
    }

    public void setColumna20(String columna20) {
        this.columna20 = columna20;
    }
    

}
