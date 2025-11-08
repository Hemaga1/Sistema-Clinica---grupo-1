package persistencia;


import modelo.personas.Asociado;

public class AsociadoMapper {

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