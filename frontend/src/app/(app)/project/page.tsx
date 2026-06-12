"use client";

import { getProjects } from "@/services/project.service";
import { ProjectResponse, projects } from "@/types/types";
import { useEffect, useState } from "react";

export default function ProjectPage() {
    const [projects, setProjects] = useState<projects[]>([]);

    useEffect(() => {
        async function getProject() {
            try {
                const data: ProjectResponse = await getProjects();
                setProjects(data.projects);
            } catch (Error) {
                console.log(Error)
            }
        }
        getProject();
    }, [])

    return (
        <div className="min-h-dvh w-full flex flex-col bg-gradient-to-r from-[rgb(33,147,176)] to-[rgb(109,213,237)]">

            <header className="h-[15dvh] w-full bg-white/10 backdrop-blur-md flex items-center justify-between px-8 shadow-md border-b border-white/20">
                <h1 className="text-white text-lg font-semibold">
                    Aqui estan todos tus proyectos
                </h1>
            </header>
            <main className="flex-1 flex flex-col justify-center items-center gap-4 px-6 py-6">
                {projects.map((project) => (
                    <div
                        key={project.id}
                        className="h-[10dvh] w-full max-w-3xl h-[70px] rounded-xl bg-white flex items-center px-5 shadow-md"
                    >
                        <h1 className="text-black text-sm font-medium">
                            {project.name}
                        </h1>
                    </div>
                ))}
            </main>

        </div>
    );
}