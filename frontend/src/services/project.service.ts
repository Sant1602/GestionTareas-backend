
import { ENVS } from "@/config/constant";
import { ProjectResponse } from "@/types/types";

export async function getProjects(): Promise<ProjectResponse> {
    const res = await fetch(`${ENVS.API_URL}/projects`, {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    })
    const data = await res.json();
    if(!res.ok){
        throw new Error("No se pudo traer los proyectos")
    }
    return data;
}