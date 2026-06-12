"use client"

import { Button, Input, TextField, Label } from "@heroui/react";
import { login, LoginRequest } from "@/services/login.service";
import { useState } from "react";
import { useRouter } from "next/navigation";

export default function LoginForm() {

    const router = useRouter()
    const [account, setAccount] = useState(true)
    const [loginData, setLoginData] = useState<LoginRequest>({
        username: "",
        password: ""
    })

    const forgotPassword = () => {
        router.push("/auth/forgot-password");
    };

    const handleSubmit = async () => {
        const res = await login(loginData)

        if (res.ok) {
            router.push("/dashboard")
        } else {
            alert("Usuario o contraseña incorrectos")
        }
    }

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div className="relative h-screen flex items-center justify-center overflow-hidden">

            <div
                className="absolute inset-0 bg-cover bg-center bg-no-repeat scale-105"
                style={{
                    filter: "contrast(1.15) saturate(1.1) brightness(1.05)",
                    backgroundImage: "url('login.png')"
                }}
            />

            <div className="absolute inset-0 bg-gradient-to-b from-black/10 via-black/20 to-black/30" />
            <div className="
                h-auto md:h-[70%]
                w-[90%] sm:w-[80%] md:w-[70%] lg:w-[55%]
                bg-white relative z-10 rounded-4xl
                flex flex-col md:flex-row 
            ">
                <div className={`
                    w-full md:w-1/2
                    bg-gray-200 md:rounded-l-4xl rounded-t-4xl md:rounded-tr-none
                    flex flex-col items-center justify-center px-6 py-8 space-y-5
                    transition-all duration-300
                    ${account ? "opacity-0 pointer-events-none scale-95" : "opacity-100 scale-100"}
                `}>
                    <h1 className="text-3xl font-bold font-open-sans text-blue-700 text-center">
                        Regístrate
                    </h1>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-3 w-full max-w-lg">

                        <TextField fullWidth>
                            <Input name="username" placeholder="Usuario" onChange={handleChange}
                                className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <TextField fullWidth>
                            <Input name="name" placeholder="Nombre" onChange={handleChange}
                                className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <TextField fullWidth>
                            <Input name="last_name" placeholder="Primer Apellido" onChange={handleChange}
                                className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <TextField fullWidth>
                            <Input name="second_last_name" placeholder="Segundo Apellido" onChange={handleChange}
                                className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <TextField fullWidth type="password">
                            <Input name="password" placeholder="Contraseña" onChange={handleChange}
                                className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <TextField fullWidth type="password">
                            <Input name="confirmPassword" placeholder="Confirmar Contraseña" onChange={handleChange}
                                className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <div className="col-span-1 md:col-span-2">
                            <TextField fullWidth>
                                <Input name="email" placeholder="Correo electrónico" onChange={handleChange}
                                    className="bg-gray-100 h-10 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                            </TextField>
                        </div>

                        <div className="col-span-1 md:col-span-2 flex flex-col items-center gap-y-5">
                            <Button
                                className="h-10 px-20 text-white font-bebas-neue font-semibold bg-blue-500 rounded-lg shadow-md transition-all duration-150 hover:-translate-y-1 hover:shadow-lg active:translate-y-1 active:shadow-sm"
                                onClick={handleSubmit}
                            >
                                Registrarse
                            </Button>

                            <i className="text-black cursor-pointer" onClick={() => setAccount(true)}>
                                ¿Tienes cuenta?
                            </i>
                        </div>

                    </div>
                </div>
                <div className={`
                    w-full md:w-1/2
                    bg-white md:rounded-r-4xl rounded-b-4xl md:rounded-bl-none
                    flex flex-col items-center justify-center py-8
                    transition-all duration-300
                    ${account ? "opacity-100 scale-100" : "opacity-0 pointer-events-none scale-95"}
                `}>
                    <div className="flex flex-col items-center space-y-10 w-full">

                        <h1 className="text-3xl font-bold font-open-sans text-blue-700">
                            Iniciar Sesion
                        </h1>

                        <div className="flex flex-col items-center space-y-7 w-full px-6">

                            <TextField fullWidth>
                                <Input
                                    value={loginData.username}
                                    name="username"
                                    placeholder="Usuario"
                                    onChange={handleChange}
                                    className="bg-gray-100 h-10 w-full md:w-70 px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none"
                                />
                            </TextField>

                            <TextField fullWidth type="password">
                                <Input
                                    value={loginData.password}
                                    name="password"
                                    placeholder="Contraseña"
                                    onChange={handleChange}
                                    className="bg-gray-100 h-10 w-full md:w-70 px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none"
                                />
                            </TextField>

                            <Button
                                className="h-10 px-20 text-white font-bebas-neue font-semibold bg-blue-500 rounded-lg shadow-md transition-all duration-150 hover:-translate-y-1 hover:shadow-lg active:translate-y-1 active:shadow-sm"
                                onClick={handleSubmit}
                            >
                                Ingresar
                            </Button>

                            <div className="flex flex-col items-center gap-y-1">
                                <i className="text-black cursor-pointer" onClick={forgotPassword}>
                                    ¿Olvidaste tu contraseña?
                                </i>
                                <i className="text-black cursor-pointer" onClick={() => setAccount(false)}>
                                    ¿No tienes cuenta?
                                </i>
                            </div>


                        </div>

                    </div>
                </div>

            </div>
        </div>
    )
}