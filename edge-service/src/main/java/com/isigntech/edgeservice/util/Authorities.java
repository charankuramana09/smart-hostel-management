package com.isigntech.edgeservice.util;

public enum Authorities {
	ADMIN("ROLE_ADMIN"),
    SUPERADMIN("ROLE_SUPERADMIN"),
    SUPERVISOR("ROLE_SUPERVISOR"),
    USER("ROLE_USER");

    private final String roleName;

    Authorities(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
