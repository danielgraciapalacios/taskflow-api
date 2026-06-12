package com.danielgraciapalacios.taskflowapi.dto;

import com.danielgraciapalacios.taskflowapi.entity.TaskList;

import java.time.Instant;

/**
 * Datos que la API devuelve al cliente al consultar una lista de tareas.
 * Incluye campos de solo lectura (id, fechas de auditoría) que el cliente nunca debe enviar.
 */
public record TaskListResponse(
        Long id,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt) {

    /**
     * Convierte una entidad JPA en su DTO de respuesta.
     * Vive aquí (y no en la entidad) para que la entidad no dependa de la capa de presentación.
     */
    public static TaskListResponse from(TaskList taskList) {
        return new TaskListResponse(
                taskList.getId(),
                taskList.getName(),
                taskList.getDescription(),
                taskList.getCreatedAt(),
                taskList.getUpdatedAt());
    }
}
