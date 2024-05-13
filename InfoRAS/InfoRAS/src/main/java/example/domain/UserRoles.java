package example.domain;

//Los roles deben llamarse así porque si no,
// spring te devuelve un 403 aunque lo tengas todo PERFECTO
//Los roles deben tener un ROLE_ delante del nombre.
public enum UserRoles {
    ROLE_ADMIN,
    ROLE_USER
}