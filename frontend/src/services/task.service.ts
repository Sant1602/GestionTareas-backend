import { ENVS } from "@/config/constant"

export enum StatusTask {
    Pendiente = "Pendiente",
    En_Proceso = "En_Proceso",
    Finalizado = "Finalizado"
}

export interface TaskRequest {
    title: string;
    description: string;
    userId: number;
    status: StatusTask
}


export async function getTasks() {
    const res = await fetch(`${ENVS.API_URL}/tasks`, {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    })
    if (!res.ok) {
        throw new Error("No sepuedo obtener las tareas")
    }
    const data = await res.json();
    return data
}

export async function getTaskBoard(boardId: number) {
    const res = await fetch(`${ENVS.API_URL}/tasks/boardId/${boardId}`, {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    })
    if (!res.ok) {
        throw new Error("No sepuedo obtener las tareas")
    }
    const data = await res.json();
    return data
}

export async function createTasks(formData: TaskRequest) {
    const res = await fetch(`${ENVS.API_URL}/tasks`, {
        method: "POST",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    if (!res.ok) {
        throw new Error("No se pudo crear la tarea")
    }
    return { ok: res.ok }
}

export async function changeStatus(taskId: number, status: string) {
    const data = {
        status: status
    }
    const res = await fetch(`${ENVS.API_URL}/tasks/${taskId}`, {
        method: "PATCH",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    })
    if (!res.ok) {
        throw new Error("No se puedo actualizar el estado de la tarea")
    }
    return { ok: res.ok }
}