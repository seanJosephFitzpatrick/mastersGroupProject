package com.mase2.mase2_project.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the base_data database table.
 * 
 */
@Entity
@Table(name="base_data")
@NamedQuery(name="BaseData.findAll", query="SELECT b FROM BaseData b")
public class BaseData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BaseDataPK id;

	@Column(name="cell_id")
	private int cellId;

	private int duration;

	@Column(name="hier3_id", precision=10)
	private BigDecimal hier3Id;

	@Column(name="hier32_id", precision=10)
	private BigDecimal hier32Id;

	@Column(name="hier321_id", precision=10)
	private BigDecimal hier321Id;

	@Column(precision=10)
	private BigDecimal imsi;

	@Column(name="ne_version", length=5)
	private String neVersion;

	//bi-directional many-to-one association to EventCause
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cause_code", referencedColumnName="event_code", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="event_id", referencedColumnName="event_id", nullable=false, insertable=false, updatable=false)
		})
	private EventCause eventCause;

	//bi-directional many-to-one association to Ue
	@ManyToOne
	@JoinColumn(name="ue_type")
	private Ue ue;

	//bi-directional many-to-one association to MccMnc
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="market", referencedColumnName="mcc"),
		@JoinColumn(name="operator", referencedColumnName="mnc")
		})
	private MccMnc mccMnc;

	//bi-directional many-to-one association to FailureClass
	@ManyToOne
	@JoinColumn(name="failure_class")
	private FailureClass failureClassBean;

	public BaseData() {
	}

	public BaseDataPK getId() {
		return this.id;
	}

	public void setId(BaseDataPK id) {
		this.id = id;
	}

	public int getCellId() {
		return this.cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getHier3Id() {
		return this.hier3Id;
	}

	public void setHier3Id(BigDecimal hier3Id) {
		this.hier3Id = hier3Id;
	}

	public BigDecimal getHier32Id() {
		return this.hier32Id;
	}

	public void setHier32Id(BigDecimal hier32Id) {
		this.hier32Id = hier32Id;
	}

	public BigDecimal getHier321Id() {
		return this.hier321Id;
	}

	public void setHier321Id(BigDecimal hier321Id) {
		this.hier321Id = hier321Id;
	}

	public BigDecimal getImsi() {
		return this.imsi;
	}

	public void setImsi(BigDecimal imsi) {
		this.imsi = imsi;
	}

	public String getNeVersion() {
		return this.neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public EventCause getEventCause() {
		return this.eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	public Ue getUe() {
		return this.ue;
	}

	public void setUe(Ue ue) {
		this.ue = ue;
	}

	public MccMnc getMccMnc() {
		return this.mccMnc;
	}

	public void setMccMnc(MccMnc mccMnc) {
		this.mccMnc = mccMnc;
	}

	public FailureClass getFailureClassBean() {
		return this.failureClassBean;
	}

	public void setFailureClassBean(FailureClass failureClassBean) {
		this.failureClassBean = failureClassBean;
	}

}