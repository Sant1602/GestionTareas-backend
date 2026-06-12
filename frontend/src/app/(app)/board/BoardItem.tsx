"use client"

import { useState, useEffect } from "react";
import { toast } from "sonner";
import TaskForm from "@/components/CardTaks";
import { SectionsDashboard, Sections } from "./board.data";
import { getTaskBoard, StatusTask } from "@/services/task.service";
import { DndContext, useDroppable, useDraggable } from "@dnd-kit/core";
import { changeStatus } from "@/services/task.service";
import { useParams, useSearchParams } from "next/navigation";
import { getMe } from "@/services/login.service";
import { UserInfo } from "@/types/types";

export interface Tasks {
    id: number,
    title: string,
    description: string,
    status: StatusTask
    userID: number
}

export default function Board() {
    const params = useParams();
    const searchParams = useSearchParams();
    const boardId = Number(params.boardId);
    const boardName = searchParams.get("name") ?? "Tablero";

    const section: Sections[] = SectionsDashboard;
    const [addTaks, setAddTask] = useState(false);
    const [task, setTask] = useState<Tasks[]>([]);
    const [user, setUser] = useState<UserInfo | null>(null);
    const [selectedSectionId, setSelectedSectionId] = useState<Sections | null>(null)

    useEffect(() => {
        async function Sections() {
            try {
                console.log(boardId)
                const data = await getTaskBoard(boardId);
                setTask(data)
            } catch (error) {
                toast.error(error instanceof Error ? error.message : "Error desconocido");
            }
        }
        Sections()
    }, [])

    useEffect(() => {
        async function User() {
            try {
                const data: UserInfo = await getMe();
                console.log(data)
                setUser(data)
            } catch (error) {
                toast.error(error instanceof Error ? error.message : "Error desconocido");
            }
        }
        User()
    }, [])

    const handleDragEnd = async (event: any) => {
        const { active, over } = event;
        if (!over) return;
        const taskId = active.id;
        const newStatus = over.id;
        const previousTasks = task;
        setTask(prev =>
            prev.map(t =>
                t.id === taskId ? { ...t, status: newStatus } : t
            )
        );

        try {
            await changeStatus(taskId, newStatus);
        } catch (error) {
            console.error(error);
            setTask(previousTasks);
            toast.error("No se pudo actualizar el estado");
        }
    };

    function Column({ s, children }: { s: Sections, children: React.ReactNode }) {
        const { setNodeRef } = useDroppable({
            id: s.status
        });

        return (
            <div
                ref={setNodeRef}
                className="flex-1 bg-white/40 rounded-lg p-4 flex flex-col gap-2"
            >
                {children}
            </div>
        );
    }

    function TaskCard({ t }: { t: Tasks }) {
        const { attributes, listeners, setNodeRef, transform } = useDraggable({
            id: t.id
        });

        const style = transform
            ? { transform: `translate(${transform.x}px, ${transform.y}px)` }
            : undefined;

        return (
            <button
                ref={setNodeRef}
                {...listeners}
                {...attributes}
                style={style}
                className="min-h-[60px] bg-white rounded-lg p-2 cursor-pointer flex items-center"
            >
                <span className="text-black text-xs sm:text-sm md:text-base break-words line-clamp-2">
                    {t.title}
                </span>
            </button>
        );
    }

    return (
        <div className="min-h-dvh w-full flex flex-col bg-gradient-to-r from-[rgb(33,147,176)] to-[rgb(109,213,237)]">

            <header className="h-[15dvh] w-full bg-white/10 backdrop-blur-md flex items-center justify-between px-8 shadow-md border-b-2 border-gray-300">
                <h1 className="text-white text-2xl font-bold">Claudia</h1>
                <div className="flex items-center gap-2">
                    <div className="w-8 h-8 rounded-full bg-gray-500 flex items-center justify-center text-white font-semibold">
                        {user?.name.charAt(0).toUpperCase()}{user?.lastName.charAt(0).toUpperCase()}
                    </div>
                </div>
            </header>
            <main className="w-full flex flex-col gap-y-2 pb-6 flex-1">

                <div className="h-[10dvh] w-full bg-blue-900 backdrop-blur-md shadow-md flex items-center px-6">
                    <div className="flex-1">
                        <h2 className="text-white font-semibold">
                            {boardName}
                        </h2>
                    </div>
                    <div className="flex-1 flex justify-center gap-6">
                        <h2 className="text-white">Colaboradores</h2>
                        <h2 className="text-white">Filtros</h2>
                    </div>
                    <div className="flex-1 flex justify-end">
                        <button className="bg-gray-200 px-4 py-2 rounded cursor-pointer text-black">
                            Compartir
                        </button>
                    </div>
                </div>
                <DndContext onDragEnd={handleDragEnd}>
                    <div className="flex-1 bg-white/20 backdrop-blur-md rounded-lg p-6 shadow-md overflow-auto flex gap-4">
                        {section.map((s) =>
                            <Column key={s.id} s={s}>
                                <div className="flex justify-center items-center mb-2">
                                    <h2 className="font-semibold text-black text-sm sm:text-base md:text-lg lg:text-xl text-center break-words">
                                        {s.name}
                                    </h2>
                                </div>

                                {task
                                    .filter((t) => t.status === s.status)
                                    .map((t) => (
                                        <TaskCard key={t.id} t={t} />
                                    ))}

                                <button
                                    className="w-full flex bg-white justify-center items-center rounded-lg cursor-pointer px-4 py-2"
                                    onClick={() => {
                                        setAddTask(true);
                                        setSelectedSectionId(s);
                                    }}
                                >
                                    <span className="text-black font-bold text-sm sm:text-base">
                                        + Añadir
                                    </span>
                                </button>

                            </Column>
                        )}
                    </div>
                </DndContext>
            </main>
            {
                addTaks && selectedSectionId && (
                    <>
                        <div
                            className={`fixed inset-0 bg-black z-40 transition-opacity duration-300 
                            ${addTaks ? 'opacity-50' : 'opacity-0'}`}
                            onClick={() => setAddTask(false)}
                        />
                        <div
                            className={`fixed top-1/2 left-1/2 z-50 transform -translate-x-1/2 -translate-y-1/2
                                w-[90%] max-w-5xl bg-white rounded-xl shadow-2xl p-8
                                max-h-[90vh] overflow-auto
                                transition-all duration-300 ${addTaks ? 'scale-100 opacity-100' : 'scale-75 opacity-0'}`}
                        >
                            <TaskForm
                                Section={selectedSectionId}
                                onClose={() => setAddTask(false)}
                            />
                        </div>
                    </>
                )
            }
        </div >
    );
}