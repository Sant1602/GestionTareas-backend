"use client"

import { Button, Input, TextField, Label } from "@heroui/react";
import { login, LoginRequest, sendEmail } from "@/services/login.service";
import { useState } from "react";
import { useRouter } from "next/navigation";

export default function ForgotPassword() {

    const [email, setEmail] = useState("")

    const handleSubmit = async () => {
        console.log(email)
        const res = await sendEmail(email)

        if (res.ok) {
            alert("Correo de recuperacion enviado con exito");
        } else {
            alert("Error al enviar el correo de recuperacion")
        }
    }

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value)
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
                    w-full md:full
                    bg-gray-200 md:rounded-4xl
                    flex flex-col items-center justify-center px-6 py-8 space-y-5
                    transition-all duration-300
                `}>
                    <h1 className="text-3xl font-bold font-open-sans text-blue-700 text-center">
                        Recuperar Contraseña
                    </h1>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-3 w-full max-w-lg">
                        <div className="flex flex-col col-span-1 md:col-span-2 gap-y-5">
                            <TextField fullWidth>
                                <h2 className="text-black text-center">Ingresa tu correo electronico para poderte enviar un codigo de recuperacion</h2>
                            </TextField>

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
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}