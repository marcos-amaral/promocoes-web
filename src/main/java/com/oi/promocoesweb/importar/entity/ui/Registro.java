/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.entity.ui;

import com.oi.promocoesweb.importar.utils.PoiUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 *
 * @author mmouraam
 */
public class Registro implements Comparable<Registro> {

    private static final Logger logger = LogManager.getLogger();

    int originalRow;
    Map<String, String> chaves = new HashMap<>();
    Map<String, String> colunas = new HashMap<>();
    Map<String, String> limites = new HashMap<>();

    List<Prompt> prompts = new ArrayList<>();

    public Registro(XSSFRow row, List<Campo> campos) {
        Pattern COLUMN_PATTERN = Pattern.compile("([a-zA-Z0-9_]*)(!\\[(.*)\\])?(.*)?");
        String colunaTemp = "";
        String promptTemp = "";
        boolean ignorarColuna = false;

        originalRow = row.getRowNum();

        for (int j = 0; j < campos.size(); j++) {
            try {
                Campo campo = campos.get(j);
                Cell next = row.getCell(j);

                switch (campo.getTipo()) {
                    case CHAVE:
                        logger.trace("{}|nome: {}|{}|{}", row.getRowNum(), campo.getNome().toUpperCase(), PoiUtils.getValue(next, campo), PoiUtils.getValueAsString(next, campo).getValue());
                        if (!"".equals(PoiUtils.getValueAsString(next, campo).getValue())) {
                            chaves.put(campo.getNomeColuna() != null && !"".equals(campo.getNomeColuna()) ? campo.getNomeColuna().toUpperCase() : campo.getNome().toUpperCase(), PoiUtils.getValueAsString(next, campo).getValue());
                        }

                        break;
                    case COLUNA:
                        if (!"".equals(PoiUtils.getValueAsString(next, campo).getValue())) {
                            for (Column column : campo.getColumns()) {
                                if (column.getSubstringValue().contains("!")) {
                                    Matcher matcher = COLUMN_PATTERN.matcher(column.getSubstringValue());

                                    if (matcher.matches()) {
                                        String valueAsString = PoiUtils.getValueAsString(next, campo).getValue().toUpperCase();
                                        if (valueAsString.contains(matcher.group(1)) && !valueAsString.replace(matcher.group(1), "").contains(matcher.group(3))) {
                                            colunaTemp = column.getColumnName();
                                            ignorarColuna = false;

                                            promptTemp = PoiUtils.getValueAsString(next, campo).getValue();

                                            break;
                                        } 
                                    }
                                }
                                if (PoiUtils.getValueAsString(next, campo).getValue().toUpperCase().contains(column.getSubstringValue())) {
                                    colunaTemp = column.getColumnName();
                                    ignorarColuna = false;

                                    promptTemp = PoiUtils.getValueAsString(next, campo).getValue();

                                    break;
                                } else {
                                    colunaTemp = "";
                                    ignorarColuna = true;
                                }
                            }
                        }
                        break;
                    case VALOR:
                        if (!"".equals(colunaTemp)) {
                            prompts.add(new Prompt(promptTemp, PoiUtils.getValueAsString(next, campo).getValue()));

                            if (colunas.containsKey(colunaTemp)) {
                                colunas.put(colunaTemp, colunas.get(colunaTemp) + PoiUtils.getValueAsString(next, campo).getValue());
                            } else {
                                colunas.put(colunaTemp, PoiUtils.getValueAsString(next, campo).getValue());
                            }
                        } else if (!ignorarColuna) {
                            colunas.put(campo.getNomeColuna() != null && !"".equals(campo.getNomeColuna()) ? campo.getNomeColuna().toUpperCase() : campo.getNome().toUpperCase(), PoiUtils.getValueAsString(next, campo).getValue());
                        }
                        break;
                    case LIMITE:
                        if (!"".equals(PoiUtils.getValueAsString(next, campo).getValue())) {
                            if (!"".equals(colunaTemp)) {
                                limites.put(colunaTemp, PoiUtils.getValueAsString(next, campo).getValue());
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "Registro{" + "chaves=" + chaves.values() + ", colunas=" + colunas.values() + '}';
    }

    public void merge(Registro registro) {
        for (Map.Entry<String, String> entry : registro.getColunas().entrySet()) {
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();

            if (colunas.containsKey(key)) {
                colunas.put(key, colunas.get(key) + val);
            } else {
                colunas.put(key, val);
            }
        }

        prompts.addAll(registro.getPrompts());
    }

    public String getChaveStr() {
        List<String> keyList = getKeysChavesList();
        Collections.sort(keyList);

        StringBuilder chaveStr = new StringBuilder();
        for (String string : keyList) {
            chaveStr.append(chaves.get(string));
        }

        return chaveStr.toString();
    }

    public Map<String, String> getChaves() {
        return chaves;
    }

    public List<String> getValuesChavesList() {
        return chaves.values().stream().collect(Collectors.toList());
    }

    public List<String> getKeysChavesList() {
        return chaves.keySet().stream().collect(Collectors.toList());
    }

    public void setChaves(Map<String, String> chaves) {
        this.chaves = chaves;
    }

    public Map<String, String> getColunas() {
        return colunas;
    }

    public List<String> getColunasKeysList() {
        return colunas.keySet().stream().collect(Collectors.toList());
    }

    public List<String> getColunasValuesList() {
        return colunas.values().stream().collect(Collectors.toList());
    }

    public void setColunas(Map<String, String> colunas) {
        this.colunas = colunas;
    }

    public List<Prompt> getPrompts() {
        return prompts;
    }

    public void setPrompts(List<Prompt> prompts) {
        this.prompts = prompts;
    }

    @Override
    public int compareTo(Registro o) {
        return this.originalRow > o.originalRow ? 1 : (this.originalRow < o.originalRow ? -1 : 0);
    }

}
