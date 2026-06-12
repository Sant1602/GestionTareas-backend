import { StatusTask } from "@/services/task.service";

export interface Sections {
    id: number,
    name: string,
    status: StatusTask
}

export const SectionsDashboard: Sections[] = [
    { id: 1, name: "Pendiente", status: StatusTask.Pendiente },
    { id: 2, name: "En proceso", status: StatusTask.En_Proceso },
    { id: 3, name: "Finalizado", status: StatusTask.Finalizado }
];
