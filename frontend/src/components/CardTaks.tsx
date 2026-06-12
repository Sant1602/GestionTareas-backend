// components/TaskForm.tsx
"use client";

import { useState } from "react";
import { Sections } from "@/app/dashboard/dashboard.data";
import { createTasks, TaskRequest } from "@/services/task.service";

export default function TaskForm({Section, onClose} : {Section: Sections, onClose: () => void}) {
    const [form, setForm] = useState<TaskRequest>({
        title: "",
        description: "",
        userId: 0,
        status: Section.status
    });

    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = e.target;
        setForm((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async () => {
        await createTasks(form);
        window.location.reload();
    }

    return (
        <div className="max-w-lg mx-auto p-6 border rounded-xl bg-white shadow-sm">
            <h2 className="text-lg font-medium mb-4 text-black">Crear Nueva Tarea</h2>
            <div className="mb-4">
                <label className="block text-sm  mb-1 text-black">Título *</label>
                <input
                    name="title"
                    value={form.title}
                    onChange={handleChange}
                    placeholder="Ej: Implementar autenticación JWT"
                    className="w-full border rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-1 text-black"
                />
            </div>

            <div className="mb-4">
                <label className="block text-sm mb-1 text-black">Descripción</label>
                <textarea
                    name="description"
                    value={form.description}
                    onChange={handleChange}
                    placeholder="Describe el objetivo de la tarea..."
                    rows={3}
                    className="w-full border rounded-lg px-3 py-2 text-sm resize-y focus:outline-none focus:ring-1 text-black"
                />
            </div>

            <div className="grid grid-cols-2 gap-3 mb-6">
                <div>
                    <label className="block text-sm  mb-1 text-black">Usuario *</label>
                    <input
                        type="number"
                        name="userId"
                        value={form.userId || ""}
                        onChange={handleChange}
                        placeholder="Ej: 1"
                        className="w-full border rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-1 text-black"
                    />
                </div>
            </div>
            <div className="mb-4">
                <label className="block text-sm mb-1 text-black">Estado : {Section.name}</label>
            </div>

            <div className="flex gap-2 justify-end">
                <button className="px-4 py-2 text-sm border rounded-lg text-black"
                onClick={onClose}>
                    Cancelar
                </button>
                <button
                    onClick={handleSubmit}
                    className="px-4 py-2 text-sm bg-gray-900 text-white rounded-lg font-medium text-black"
                >
                    Crear tarea
                </button>
            </div>
        </div>
    );
}