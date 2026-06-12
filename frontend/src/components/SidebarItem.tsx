"use client";

import Link from "next/link";
import { useState } from "react";
import type { NavItem } from "./Sidebar";

interface SidebarItemProps {
    item: NavItem;
    collapsed: boolean;
    active?: boolean;
}

export function SidebarItem({ item, collapsed, active = false }: SidebarItemProps) {
    const [showTooltip, setShowTooltip] = useState(false);
    const Icon = item.icon;

    const baseClasses = [
        "relative flex items-center gap-3 px-3 py-2 mx-1.5 rounded-md",
        "transition-all duration-150 cursor-pointer select-none",
        "text-[13px] font-medium",
        active
            ? "bg-[#0052CC]/20 text-[#4C9AFF]"
            : "text-white/50 hover:text-white/90 hover:bg-white/[0.06]",
        collapsed ? "justify-center" : "",
    ].join(" ");

    return (
        <div
            className="relative"
            onMouseEnter={() => collapsed && setShowTooltip(true)}
            onMouseLeave={() => setShowTooltip(false)}
        >
            <Link href={item.link? item.link: "#"} className={baseClasses}>
                {active && (
                    <span className="absolute left-0 top-1/2 -translate-y-1/2 w-0.5 h-5 bg-[#4C9AFF] rounded-r-full" />
                )}
                <span className="relative shrink-0">
                    <Icon
                        size={17}
                        strokeWidth={active ? 2.2 : 1.8}
                        className={active ? "text-[#4C9AFF]" : ""}
                    />
                </span>
                {!collapsed && (
                    <>
                        <span className="flex-1 truncate">{item.label}</span>
                        {item.badge !== undefined && item.badge > 0 && (
                            <span
                                className={[
                                    "text-[10px] font-semibold px-1.5 py-0.5 rounded-full min-w-[18px] text-center leading-none",
                                    active
                                        ? "bg-[#0052CC]/40 text-[#4C9AFF]"
                                        : "bg-white/10 text-white/50",
                                ].join(" ")}
                            >
                                {item.badge > 99 ? "99+" : item.badge}
                            </span>
                        )}
                    </>
                )}
                {collapsed && item.badge !== undefined && item.badge > 0 && (
                    <span className="absolute top-1.5 right-1.5 w-1.5 h-1.5 rounded-full bg-[#0052CC]" />
                )}
            </Link>
            {collapsed && showTooltip && (
                <div
                    className="absolute left-full top-1/2 -translate-y-1/2 ml-3 z-50 pointer-events-none"
                >
                    <div className="bg-[#1C2333] text-white text-xs font-medium px-2.5 py-1.5 rounded-md shadow-xl border border-white/[0.08] whitespace-nowrap flex items-center gap-2">
                        {item.label}
                        {item.badge !== undefined && item.badge > 0 && (
                            <span className="bg-[#0052CC]/40 text-[#4C9AFF] text-[10px] px-1.5 py-0.5 rounded-full">
                                {item.badge}
                            </span>
                        )}
                    </div>
                    <div className="absolute right-full top-1/2 -translate-y-1/2 border-4 border-transparent border-r-[#1C2333]" />
                </div>
            )}
        </div>
    );
}