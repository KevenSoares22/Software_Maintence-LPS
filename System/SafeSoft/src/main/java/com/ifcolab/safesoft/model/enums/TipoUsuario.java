package com.ifcolab.safesoft.model.enums;

public enum TipoUsuario {
    ADMIN("Administrador"),
    TECNICO("Tecnico"),
    CLIENTE("Cliente"),
    RECEPCIONISTA("Recepcionista"),
    Suporte("Suporte");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }
    
        @Override
    public String toString() {
        return this.descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static TipoUsuario fromString(String texto) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.name().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + texto);
    }
}