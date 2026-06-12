"use client";

import { useState, useEffect, useRef } from "react";
import { usePathname, useRouter } from "next/navigation";
import Link from "next/link";
import {
    LayoutDashboard,
    FolderKanban,
    CircleDot,
    BarChart3,
    Settings,
    ChevronLeft,
    ChevronRight,
    ChevronDown,
    Bell,
    Search,
    Plus,
    HelpCircle,
    LogOut,
    Folder,
    Star,
    Archive,
} from "lucide-react";
import { SidebarItem } from "./SidebarItem";
import { SidebarSection } from "./SidebarSection";
import { logoutRequest } from "@/services/login.service";
import { ProjectResponse, projects } from "@/types/types";
import { getProjects } from "@/services/project.service";

export interface NavItem {
    label: string;
    icon: React.ElementType;
    badge?: number;
    link?: string;
    hasSubmenu?: boolean;
}

interface SidebarProps {
    defaultCollapsed?: boolean;
}

export default function Sidebar({ defaultCollapsed = false }: SidebarProps) {
    const router = useRouter();
    const [collapsed, setCollapsed] = useState(defaultCollapsed);
    const [mobileOpen, setMobileOpen] = useState(false);
    const [proyectosOpen, setProyectosOpen] = useState(false);
    const submenuRef = useRef<HTMLDivElement>(null);
    const [projects, setProjects] = useState<projects[]>([]);
    const [amount, setAmount] = useState<number>();

    const mainNavItems: NavItem[] = [
        { label: "Dashboard", icon: LayoutDashboard, link: "/dashboard" },
        { label: "Proyectos", icon: FolderKanban, badge: amount, hasSubmenu: true },
        { label: "Issues", icon: CircleDot },
        { label: "Reportes", icon: BarChart3 },
        { label: "Configuración", icon: Settings },
    ];

    const bottomNavItems: NavItem[] = [
        { label: "Notificaciones", icon: Bell, badge: 3 },
        { label: "Ayuda", icon: HelpCircle },
    ];

    useEffect(() => {
        async function projects() {
            try {
                const data: ProjectResponse = await getProjects();
                setProjects(data.projects);
                setAmount(data.amount);
            } catch (Error) {
                console.log("Hubo un error", Error)
            }
        }
        projects();
    }, [])

    useEffect(() => {
        const handleResize = () => {
            if (window.innerWidth < 768) {
                setCollapsed(true);
            }
        };
        window.addEventListener("resize", handleResize);
        handleResize();
        return () => window.removeEventListener("resize", handleResize);
    }, []);

    useEffect(() => {
        const el = submenuRef.current;
        if (!el) return;
        if (proyectosOpen) {
            el.style.maxHeight = el.scrollHeight + "px";
            el.style.opacity = "1";
        } else {
            el.style.maxHeight = "0px";
            el.style.opacity = "0";
        }
    }, [proyectosOpen]);

    useEffect(() => {
        if (collapsed) setProyectosOpen(false);
    }, [collapsed]);

    const sidebarWidth = collapsed ? "w-[60px]" : "w-[240px]";

    return (
        <>
            {mobileOpen && (
                <div
                    className="fixed inset-0 bg-black/50 z-30 md:hidden"
                    onClick={() => setMobileOpen(false)}
                />
            )}
            <button
                onClick={() => setMobileOpen(!mobileOpen)}
                className="fixed top-4 left-4 z-50 md:hidden bg-[#1C2333] text-white p-2 rounded-md shadow-lg"
                aria-label="Toggle sidebar"
            >
                <FolderKanban size={18} />
            </button>

            <aside
                className={[
                    "fixed top-0 left-0 h-screen z-40 flex flex-col",
                    "bg-[#0D1117] border-r border-white/[0.06]",
                    "transition-all duration-300 ease-in-out",
                    sidebarWidth,
                    "max-md:-translate-x-full max-md:w-[240px]",
                    mobileOpen ? "max-md:translate-x-0" : "",
                ].join(" ")}
            >
                <div
                    className={[
                        "flex items-center h-14 px-3 shrink-0 border-b border-white/[0.06]",
                        collapsed ? "justify-center" : "justify-between",
                    ].join(" ")}
                >
                    {!collapsed && (
                        <Link href="/" className="flex items-center gap-2.5 min-w-0">
                            <div className="w-7 h-7 rounded bg-[#0052CC] flex items-center justify-center shrink-0">
                                <span className="text-white font-bold text-xs tracking-tight">S</span>
                            </div>
                            <span className="text-white font-semibold text-[15px] truncate tracking-tight">
                                MiApp
                            </span>
                        </Link>
                    )}
                    {collapsed && (
                        <div className="w-7 h-7 rounded bg-[#0052CC] flex items-center justify-center">
                            <span className="text-white font-bold text-xs">S</span>
                        </div>
                    )}
                    {!collapsed && (
                        <button
                            onClick={() => setCollapsed(true)}
                            className="text-white/40 hover:text-white/80 transition-colors p-1 rounded hover:bg-white/[0.07]"
                            aria-label="Colapsar sidebar"
                        >
                            <ChevronLeft size={16} />
                        </button>
                    )}
                </div>
                {!collapsed && (
                    <div className="px-3 py-2.5 flex gap-2 border-b border-white/[0.06]">
                        <button className="flex-1 flex items-center justify-center gap-1.5 bg-[#0052CC] hover:bg-[#0065FF] text-white text-xs font-medium py-1.5 rounded transition-colors">
                            <Plus size={13} />
                            Crear
                        </button>
                        <button className="flex items-center justify-center p-1.5 text-white/40 hover:text-white/80 hover:bg-white/[0.07] rounded transition-colors">
                            <Search size={15} />
                        </button>
                    </div>
                )}
                {collapsed && (
                    <div className="px-3 py-2.5 flex flex-col gap-1.5 border-b border-white/[0.06]">
                        <button className="flex items-center justify-center p-1.5 bg-[#0052CC] hover:bg-[#0065FF] text-white rounded transition-colors" title="Crear">
                            <Plus size={15} />
                        </button>
                        <button className="flex items-center justify-center p-1.5 text-white/40 hover:text-white/80 hover:bg-white/[0.07] rounded transition-colors" title="Buscar">
                            <Search size={15} />
                        </button>
                    </div>
                )}
                <nav className="flex-1 overflow-y-auto overflow-x-hidden py-2 scrollbar-thin">
                    <SidebarSection label="Menú principal" collapsed={collapsed}>
                        {mainNavItems.map((item, index) => {
                            if (item.hasSubmenu && !collapsed) {
                                return (
                                    <div key={index}>
                                        <button
                                            onClick={() => setProyectosOpen((prev) => !prev)}
                                            className={[
                                                "w-full flex items-center gap-3 px-3 py-2 rounded-sm",
                                                "text-white/60 hover:text-white hover:bg-white/[0.06]",
                                                "transition-all duration-150 group",
                                                proyectosOpen ? "text-white bg-white/[0.04]" : "",
                                            ].join(" ")}
                                        >
                                            <item.icon size={17} className="shrink-0" />
                                            <span className="flex-1 text-left text-[13px] font-medium truncate">
                                                {item.label}
                                            </span>
                                            {item.badge && (
                                                <span className="text-[10px] font-semibold bg-white/[0.10] text-white/50 rounded px-1.5 py-0.5">
                                                    {item.badge}
                                                </span>
                                            )}
                                            <ChevronDown
                                                size={13}
                                                className={[
                                                    "text-white/30 shrink-0 transition-transform duration-200",
                                                    proyectosOpen ? "rotate-180" : "",
                                                ].join(" ")}
                                            />
                                        </button>
                                        <div
                                            ref={submenuRef}
                                            style={{
                                                maxHeight: "0px",
                                                opacity: 0,
                                                overflow: "hidden",
                                                transition: "max-height 250ms ease, opacity 200ms ease",
                                            }}
                                        >
                                            <ul className="pb-1">

                                                {projects.map((project) => (
                                                    <li key={project.id}>
                                                        <Link
                                                            href={`/project/${project.id}`}
                                                            className={[
                                                                "flex items-center gap-2.5 pl-8 pr-3 py-1.5 mx-1 rounded-sm",
                                                                "text-white/50 hover:text-white hover:bg-white/[0.06]",
                                                                "transition-all duration-150 group/project",
                                                            ].join(" ")}
                                                        >
                                                            <span className="flex-1 text-[12.5px] font-medium truncate">
                                                                {project.name}
                                                            </span>
                                                        </Link>
                                                    </li>
                                                ))}
                                                <li>
                                                    <Link
                                                        href="/project"
                                                        className={[
                                                            "flex items-center gap-2.5 pl-8 pr-3 py-1.5 mx-1 rounded-sm",
                                                            "text-white/30 hover:text-white/60 hover:bg-white/[0.04]",
                                                            "transition-all duration-150 text-[12px]",
                                                        ].join(" ")}
                                                    >
                                                        <Archive size={12} className="shrink-0" />
                                                        <span>Ver todos los proyectos</span>
                                                    </Link>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                );
                            }
                            return (
                                <SidebarItem
                                    key={index}
                                    item={item}
                                    collapsed={collapsed}
                                />
                            );
                        })}
                    </SidebarSection>
                </nav>
                <div className="border-t border-white/[0.06] py-2">
                    {bottomNavItems.map((item, index) => (
                        <SidebarItem
                            key={index}
                            item={item}
                            collapsed={collapsed}
                        />
                    ))}
                    <button
                        className={[
                            "w-full flex items-center gap-3 px-3 py-2 text-white/50 hover:text-red-400",
                            "hover:bg-white/[0.06] rounded-sm transition-all duration-150",
                            collapsed ? "justify-center" : "",
                        ].join(" ")}
                        title="Cerrar sesión"
                        onClick={async () => { await logoutRequest(); window.location.reload(); }}
                    >
                        <LogOut size={17} />
                        {!collapsed && <span className="text-[13px] font-medium">Cerrar sesión</span>}
                    </button>
                </div>
                {collapsed && (
                    <button
                        onClick={() => setCollapsed(false)}
                        className="absolute -right-3 top-1/2 -translate-y-1/2 w-6 h-6 bg-[#1C2333] border border-white/[0.12] rounded-full flex items-center justify-center text-white/60 hover:text-white hover:bg-[#2D3748] transition-all shadow-md"
                        aria-label="Expandir sidebar"
                    >
                        <ChevronRight size={12} />
                    </button>
                )}
            </aside>

            <div
                className={[
                    "transition-all duration-300 ease-in-out md:block hidden",
                    collapsed ? "ml-[60px]" : "ml-[240px]",
                ].join(" ")}
            />
        </>
    );
}