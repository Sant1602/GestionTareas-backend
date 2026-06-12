"use client";

import { getProjectBoards } from "@/services/board.service";
import { boards } from "@/types/types";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

export default function ProjectBoards() {
    const params = useParams();
    const id = Number(params.id);
    const router = useRouter();
    const [boards, setBoards] = useState<boards[]>([]);

    useEffect(() => {
        async function getBoardProject() {
            try {
                const data = await getProjectBoards(id);
                setBoards(data);
            } catch (Error) {
                console.log(Error)
            }
        }
        getBoardProject();
    }, [])

    return (
        <div className="min-h-dvh w-full flex flex-col bg-gradient-to-r from-[rgb(33,147,176)] to-[rgb(109,213,237)]">

            <header className="h-[15dvh] w-full bg-white/10 backdrop-blur-md flex items-center justify-between px-8 shadow-md border-b border-white/20">
                <h1 className="text-white text-lg font-semibold">
                    Este es el proyecto #{id}
                </h1>
            </header>
            <main className="flex-1 flex flex-col justify-center items-center gap-4 px-6 py-6">
                {boards.map((board) => (
                    <button
                        key={board.Id}
                        onClick={() => router.push(`/board/${board.Id}?name=${encodeURIComponent(board.name)}`)}
                        className="h-[10dvh] w-full max-w-3xl rounded-xl h-[70px] cursor-pointer bg-white flex items-center px-5 shadow-md"
                    >
                        <h1 className="text-black text-sm font-medium">
                            {board.name}
                        </h1>
                    </button>
                ))}
            </main>

        </div>
    );
}