import { boards } from "@/types/types"
import { ENVS } from "@/config/constant"

export async function getBoards(): Promise<boards[]> {
    const res = await fetch(`${ENVS.API_URL}/boards/`, {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    })
    const data = await res.json();
    if(!res.ok){
        throw new Error("No se pudo traer los tableros")
    }
    return data;
} 

export async function getProjectBoards(projectId:number): Promise<boards[]> {
    const res = await fetch(`${ENVS.API_URL}/boards/projectId/${projectId}`, {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    })
    const data = await res.json();
    if(!res.ok){
        throw new Error("No se pudo traer los tableros")
    }
    return data;
}