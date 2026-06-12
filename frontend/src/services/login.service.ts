import { ENVS } from "@/config/constant";
import { UserInfo } from "@/types/types";

function getToken() {
    return document.cookie
        .split("; ")
        .find(row => row.startsWith("token="))
        ?.split("=")[1];
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface ForgotPasswordRequest {
    newPassword: string;
    confirmPassword: string;
    token: string;
}

interface LoginResponse {
    token: string;
}

export async function login(body: LoginRequest) {

    const res = await fetch(`${ENVS.API_URL}/auth/login`, {
        method: "POST",
        credentials: "include",
        body: JSON.stringify(body),
        headers: {
            "Content-Type": "application/json"
        }
    })
    if (!res.ok) {
        alert("Error al iniciar la sesion");
        return { ok: false }
    }
    alert("Sesion iniciada con exito");

    const data = (await res.json()) as LoginResponse;
    document.cookie = `token=${data.token}; path=/; max-age=86400; SameSite=Lax`;
    return { ok: res.ok }
}

export async function logoutRequest() {
    document.cookie = "token=; path=/; max-age=0";

    const res = await fetch(`${ENVS.API_URL}/auth/logout`, {
        method: "POST",
        credentials: "include",
    });

    return { ok: res.ok };
}

export async function sendEmail(email: string) {
    const res = await fetch(`${ENVS.API_URL}/auth/send-email`, {
        method: "POST",
        credentials: "include",
        body: JSON.stringify({ email }),
        headers: {
            "Content-Type": "application/json"
        }
    });
    return { ok: res.ok };
}

export async function sendNewPassword(Data: ForgotPasswordRequest) {
    const res = await fetch(`${ENVS.API_URL}/auth/new-password`, {
        method: "PUT",
        credentials: "include",
        body: JSON.stringify(Data),
        headers: {
            "Content-Type": "application/json"
        }
    });
    return { ok: res.ok };
}

export async function getMe(): Promise<UserInfo> {
    const res = await fetch(`${ENVS.API_URL}/auth/me`, {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${getToken()}`
        }
    });
    
    if(!res.ok){
        alert("Error al obtener la información del usuario");
        return null as unknown as UserInfo;
    }
    return await res.json();
}