package com.alura.hotel.model;

public enum FormaDePago {
	TARJETA_DE_CREDITO,
	TARJETA_DE_DEBITO,
	DINERO_EN_EFECTIVO;
	
	public static FormaDePago obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= values().length) {
            throw new IllegalArgumentException("Índice fuera de rango");
        }
        return values()[indice];
    }
}
