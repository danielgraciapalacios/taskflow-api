package com.danielgraciapalacios.taskflowapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Datos que el cliente envía para crear o actualizar una lista de tareas.
 * Es un record: inmutable, sin getters/setters boilerplate, pensado solo para transportar datos.
 */
public record TaskListRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name,

        @Size(max = 2000, message = "La descripción no puede superar los 2000 caracteres")
        String description) {
}
