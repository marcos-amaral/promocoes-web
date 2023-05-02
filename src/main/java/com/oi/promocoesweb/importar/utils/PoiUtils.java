/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.utils;

import com.oi.promocoesweb.importar.entity.CellValueHolder;
import com.oi.promocoesweb.importar.entity.ui.Campo;
import com.oi.promocoesweb.importar.enumerate.TipoDado;
import com.oi.promocoesweb.importar.exception.ImportCellValueException;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author mmouraam
 */
public class PoiUtils {

    public static CellValueHolder getValueAsString(Cell cell, Campo campo) {
        CellValueHolder cellValueHolder = new CellValueHolder();
        try {
            if (cell != null) {
                switch (cell.getCellType().toString()) {
                    case "NUMERIC":
                        if (campo.getTipoDado().equals(ValidationEnum.DATA)) {
                            if (cell.getDateCellValue() != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                cellValueHolder.setValue(sdf.format(cell.getDateCellValue()));
                                break;
                            }
                        } else if (campo.getTipoDado().equals(ValidationEnum.VALOR_UNICO)) {
                            cellValueHolder.setValue((int) cell.getNumericCellValue());
                            break;

                        } else if (campo.getTipoDado().equals(ValidationEnum.MONETARIO)) {
                            cellValueHolder.setValue(cell.getNumericCellValue());

                        } else if (campo.getTipoDado().equals(ValidationEnum.SEQUENCIA_VALORES)) {
                            String value = cell.getNumericCellValue() + "";

                            if (value.endsWith(",0") || value.endsWith(".0")) {
                                cellValueHolder.setValue(value.substring(0, value.length() - 2));
                            } else {
                                cellValueHolder.setValue(value.replace(".", ","));
                            }

                        } else {
                            throw new ImportCellValueException("NUMERIC conversion error (" + cell.getNumericCellValue() + ")!");
                        }

                    case "BLANK":
                        break;

                    case "STRING":
                        if (cell.getStringCellValue() != null) {
                            if (campo.getTipoDado().equals(TipoDado.BOOLEANO)) {
                                if (cell.getStringCellValue().length()==1 && "SsYy1".contains(cell.getStringCellValue())) {
                                    cellValueHolder.setValue("S");
                                }
                                else {
                                    cellValueHolder.setValue("N");
                                }
                            } 
                            if (campo.getTipoDado().equals(ValidationEnum.MONETARIO)) {
                                cellValueHolder.setValue(cell.getStringCellValue().replace(".", ","));
                            } 
                            else {
                                cellValueHolder.setValue(cell.getStringCellValue());
                            }
                            break;
                        }
                    default:
                        throw new ImportCellValueException("Unknown data type (" + cell.getCellType().toString() + ")!");
                }
            } else {
                throw new ImportCellValueException("null Cell!");
            }
        } catch (Exception e) {
            cellValueHolder.setException(new ImportCellValueException(e.getMessage()));
        }

        return cellValueHolder;
    }

    public static String getValue(Cell cell, Campo campo) {
        CellValueHolder cellValueHolder = new CellValueHolder();
        try {

            if (cell != null) {
                switch (cell.getCellType().toString()) {
                    case "NUMERIC":
                        return cell.getNumericCellValue() + "";

                    case "BLANK":
                        break;

                    case "STRING":
                        return cell.getStringCellValue() + "";

                    default:
                        throw new ImportCellValueException("Unknown data type (" + cell.getCellType().toString() + ")!");
                }
            } else {
                throw new ImportCellValueException("null Cell!");
            }
        } catch (Exception e) {
            cellValueHolder.setException(new ImportCellValueException(e.getMessage()));
        }

        return "";
    }

}
