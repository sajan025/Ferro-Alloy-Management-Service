package com.jsp.ferro.alloy.sms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jsp.ferro.alloy.gen.entity.BaseEntity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sms_request")
public class SmsRequest extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3000558973194566820L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
   @Column(name = "quality_request_fk_id")
	private Integer qualityRequestFkId;
	
	@Column(name = "segment")
	private String segment;
	
	@Column(name = "sub_segment")
	private String subSegment;
	
	@Column(name = "process_route")
	private String processRoute;
	
	@Column(name = "coke_petroleum_calcined")
	private Double cokePetroleumCalcined;
	
	@Column(name = "graphite_fines")
	private Double graphiteFines;
	
	@Column(name = "silico_manganese_HP")
	private Double silicoManganeseHP;
	
	@Column(name = "silico_manganes_extra_low_c")
	private Double silicoManganesExtraLowC;
	
	@Column(name = "silico_manganese_low_phos")
	private Double silicoManganeseLowPhos;
	
	@Column(name = "manganese_metal_briqutes")
	private Double manganeseMetalBriqutes;
	
	
	@Column(name = "ferro_manganese_HC")
	private Double ferroManganeseHC;
	
	@Column(name = "ferro_manganses_LC")
	private Double ferroMangansesLC;
	
	@Column(name = "ferro_silicon")
	private Double ferroSilicon;
	
	@Column(name = "aluminium_dross")
	private Double aluminiumDross;
	
	@Column(name = "ingot_AL")
	private Double ingotAL;
	
	@Column(name = "wire_AL")
	private Double wireAL;
	
	@Column(name = "wire_16_MM")
	private Double wire16MM;
	
	@Column(name = "ca_wire_pure")
	private Double caWirePure;
	
	@Column(name = "copper_cathode")
	private Double copperCathode;
	
	@Column(name = "nickel_metal")
	private Double nickelMetal;
	
	@Column(name = "ferro_chrome_LC")
	private Double ferroChromeLC;
	
	@Column(name = "ferro_chrome_CR")
	private Double ferroChromeCR;
	
	@Column(name = "ferro_niobium")
	private Double ferroNiobium;
	
	@Column(name = "wire_FE_TI_cored")
	private Double wireFETICored;
	
	@Column(name = "ferro_vanadium")
	private Double ferroVanadium;
	
	@Column(name = "nitrovan")
	private Double nitrovan;
	
	@Column(name = "ferro_molybdenum")
	private Double ferroMolybdenum;
	
	@Column(name = "ferro_boron")
	private Double ferroBoron;
			
	@Column(name = "shots_aluminium")
	private Double shotsAluminium;
					
	@Column(name = "ferro_aluminium")
	private Double ferroAluminium;
							
    @Column(name = "ferro_phosphorous")
    private Double ferroPhosphorous;
									
	@Column(name = "ferro_titaninum")
	private Double ferroTitaninum;
											
	@Column(name = "syn_slag_pre_melted")
	private Double synSlagPreMelted;
													
	@Column(name = "slag_fluidiser")
	private Double slagFluidiser;
															
	@Column(name = "wire_13_MM")
	private Double wire13MM;
																	
	@Column(name = "al_cubes")
	private Double alCubes;
																			
	@Column(name = "calcined_lime")
	private Double calcinedLime;
			
	@Column(name = "manganese_core_wire")
	private Double manganeseCoreWire;
					
	@Column(name = "total_alloy_cost_per_ton")
	private Double totalAlloyCostPerTon;
							
	@Column(name = "sms_remark")
	private String smsRemark;
}
