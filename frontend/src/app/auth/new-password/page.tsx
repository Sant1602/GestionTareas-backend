"use client"

import { Button, Input, TextField, Label } from "@heroui/react";
import { sendNewPassword, ForgotPasswordRequest} from "@/services/login.service";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { useSearchParams } from 'next/navigation';

export default function ForgotPassword() {
    const searchParams = useSearchParams();
    const tokenParam = searchParams.get("token");

    if(tokenParam == null){
        throw new Error("Error en la solicitud");
    }
    

    const router = useRouter()
    const [account, setAccount] = useState(true)
    const [passwordData, setPasswordData] = useState<ForgotPasswordRequest>({
        newPassword: "",
        confirmPassword: "",
        token: tokenParam 
    })

    const handleSubmit = async () => {
        const res = await sendNewPassword(passwordData)

        if (res.ok) {
            alert("Contraseña cambiada con éxito")
            router.push("/")
        } else {
            alert("No se pudo cambiar la contraseña")
        }
    }

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPasswordData({
            ...passwordData,
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
                    w-full md:w-full
                    bg-gray-200 md:rounded-4xl
                    flex flex-col items-center justify-center px-6 py-8 space-y-5
                    transition-all duration-300
                `}>
                    <h1 className="text-3xl font-bold font-open-sans text-blue-700 text-center">
                        Cambiar Contraseña
                    </h1>

                    <div className="flex flex-col gap-5 w-full max-w-lg">

                        <TextField fullWidth type="password">
                            <Input name="newPassword" placeholder="Nueva Contraseña" onChange={handleChange}
                                className="bg-gray-100 h-12 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <TextField fullWidth type="password">
                            <Input name="confirmPassword" placeholder="Confirmar Contraseña" onChange={handleChange}
                                className="bg-gray-100 h-12 w-full px-3 rounded-xl border border-gray-400 text-black transition-all duration-200 hover:border-sky-400 hover:shadow-[0_0_0_1px_rgb(125,211,252)] focus:border-sky-400 focus:shadow-[0_0_0_2px_rgba(125,211,252,0.6)] focus:outline-none" />
                        </TextField>

                        <div className="col-span-1 md:col-span-2 flex flex-col items-center gap-y-5">
                            <Button
                                className="h-10 px-20 text-white font-bebas-neue font-semibold bg-blue-500 rounded-lg shadow-md transition-all duration-150 hover:-translate-y-1 hover:shadow-lg active:translate-y-1 active:shadow-sm"
                                onClick={handleSubmit}
                            >
                                Confirmar
                            </Button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}