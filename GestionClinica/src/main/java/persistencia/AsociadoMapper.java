package persistencia;


import modelo.personas.Asociado;

public class AsociadoMapper {

    /**
     * Convierte un Asociado a un AsociadoDTO.
     *
     * @param a asociado del dominio
     * @return DTO equivalente, o null si  a == null}
     */
    public static AsociadoDTO toDTO(Asociado a) {
        if (a == null) return null;
        return new AsociadoDTO(
                a.getDNI(),
                a.getNombre(),
                a.getApellido(),
                a.getDomicilio().getCalle(),
                a.getDomicilio().getNumero(),
                a.getDomicilio().getCiudad(),
                a.getTelefono()
        );
    }

    /**
     * Convierte un AsociadoDTO a un Asociado del dominio.
     *
     * @param dto DTO a convertir
     * @return objeto del dominio, o null si dto == null}
     */
    public static Asociado fromDTO(AsociadoDTO dto) {
        if (dto == null) return null;
        return new Asociado(
                dto.getDni(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getCalle(),
                dto.getNumero(),
                dto.getCiudad(),
                dto.getTelefono()
        );
    }
}